package family.helpful.gateway.message.publisher;


import family.helpful.gateway.actions.EnumActionStatus;
import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.*;
import io.swagger.annotations.*;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
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
@RequestMapping(value = "/solutiontitle")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SolutionController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(SolutionController.class);

    @PostMapping("/publishSolutionContent")
    @ApiOperation(value = "Publish Content")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token", required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Publish Content OK")})
    public Message publishContent(KeycloakAuthenticationToken kat, @RequestBody SolutionContent message)
    {
        KeycloakPrincipal keycloakPrincipal= (KeycloakPrincipal) kat.getPrincipal();
        AccessToken token= keycloakPrincipal.getKeycloakSecurityContext().getToken();
        String senderUsername= token.getPreferredUsername();
        User user= new User();
        user.setUsername(senderUsername);

        message.setUser(user);

        Message resultMessage =  MessageBuilder
                .withPayload(message)
                .setHeader("action"
                        , "publishSolutionContent")
                .build();
        source.output().send(resultMessage);

          resultMessage= MessageBuilder.withPayload(message)
                .setHeader("publishSolutionContent"
                        , EnumActionStatus.SUCCESS.name()).build();

        logger.info("action", message);

        return resultMessage;
    }

    @GetMapping(value = "/all/{amount}")
    public List<SolutionTitle> getAll(@PathVariable int amount) {
        List<SolutionTitle> titleList= null;

        SolutionTitleMessage message= (SolutionTitleMessage) restClient.getForEntity("/solutiontitle/all/"+ amount,
                SolutionTitleMessage.class);

        titleList= message.getSolutionTitleList();

        return titleList;
    }


    @GetMapping(value = "/contents/{name}/{amount}")
    public List<SolutionContent> getContentsByTitle(@PathVariable String name, @PathVariable int amount) {
        List<SolutionContent> contentList= null;

        SolutionContentMessage message= (SolutionContentMessage) restClient.getForEntity("/solutiontitle/contents/"+name+"/"+amount
                , SolutionContentMessage.class);

        contentList= message.getSolutionContentList();
        return contentList;
    }

}