package family.helpful.gateway.message.publisher;


import family.helpful.gateway.actions.EnumActionStatus;
import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.*;
import family.helpful.gateway.util.KeycloakUtil;
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



@EnableBinding(Source.class)
@RestController
@RequestMapping(value = "/family")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FamilyController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(FamilyController.class);

    @PostMapping("/createFamily")
    @ApiOperation(value = "Create Family")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token",
                             required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Create Family OK")})
    public void createFamily(KeycloakAuthenticationToken kat, @RequestBody Family familyObject)
    {
        // In order to get user via Keycloak

        User user= new User();
        user.setUsername(KeycloakUtil.getUserName(kat));

        // Sets user of Family object envelope
        familyObject.setUser(user);

        // Prepare Message envelope to use it in the Persist side.
        Message familyObjectMessage =  MessageBuilder
                .withPayload(familyObject)
                .setHeader("action"
                        , "createFamily")
                .build();

        // Sends message to Persist side.
        source.output().send(familyObjectMessage);

        // It is not directly related to current algorithm and just used to be informed about process.
        logger.info("Message sent to Persist side: ", familyObject);

    }


}