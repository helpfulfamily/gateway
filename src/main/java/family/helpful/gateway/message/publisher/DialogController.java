package family.helpful.gateway.message.publisher;


import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.DialogContent;
import family.helpful.gateway.message.model.DialogContentMessage;
import family.helpful.gateway.message.model.User;
import family.helpful.gateway.util.KeycloakUtil;
import io.swagger.annotations.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@EnableBinding(Source.class)
@RestController
@RequestMapping(value = "/dialog")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DialogController {
    private static final Logger logger = LoggerFactory.getLogger(DialogController.class);
    @Autowired
    private Source source;
    @Autowired
    private RestClient restClient;

    @GetMapping(value = "/contents/{senderID}/{receiverID}/{pageNumber}")
    public List<DialogContent> getContentsByTitle(@PathVariable long senderID,
                                                  @PathVariable long receiverID,
                                                  @PathVariable int pageNumber) {
        List<DialogContent> contents = null;

        DialogContentMessage message = (DialogContentMessage) restClient
                .getForEntity("/dialog/contents/" + senderID + "/" + receiverID + "/" + pageNumber
                        , DialogContentMessage.class);

        contents = message.getContents();
        return contents;
    }

    @PostMapping("/publishDialogContent")
    @ApiOperation(value = "Publish Content")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token",
            required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Publish Content OK")})
    public Message publishContent(KeycloakAuthenticationToken kat, @RequestBody DialogContent dialogContent) {

        // In order to get user via Keycloak

        User user = new User();
        user.setUsername(KeycloakUtil.getUserName(kat));

        dialogContent.setSender(user);

        // Prepare Message envelope to use it in the Persist side.
        Message resultMessage = MessageBuilder
                .withPayload(dialogContent)
                .setHeader("action"
                        , "PUBLISH_DIALOG_CONTENT")
                .build();

        // Sends message to Persist side.
        source.output().send(resultMessage);


        // It is not directly related to current algorithm and just used to be informed about process.
        logger.info("Message sent to Persist side: ", resultMessage);

        return resultMessage;
    }


}