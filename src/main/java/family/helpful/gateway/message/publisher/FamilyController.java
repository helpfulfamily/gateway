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
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token", required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Create Family OK")})
    public Message createFamily(KeycloakAuthenticationToken kat, @RequestBody Family familyObject)
    {
        KeycloakPrincipal keycloakPrincipal= (KeycloakPrincipal) kat.getPrincipal();
        AccessToken token= keycloakPrincipal.getKeycloakSecurityContext().getToken();
        String senderUsername= token.getPreferredUsername();
        User user= new User();
        user.setUsername(senderUsername);

        familyObject.setUser(user);

        Message resultMessage =  MessageBuilder
                .withPayload(familyObject)
                .setHeader("action"
                        , "createFamily")
                .build();

        source.output().send(resultMessage);


        logger.info("action", familyObject);

        return resultMessage;
    }


}