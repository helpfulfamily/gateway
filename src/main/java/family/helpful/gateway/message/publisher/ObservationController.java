package family.helpful.gateway.message.publisher;


import family.helpful.gateway.message.model.ObservationRequestSignal;
import io.swagger.annotations.*;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;


@EnableBinding(Source.class)
@RestController
@RequestMapping("/observation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ObservationController
{
    @Autowired
    private Source source;


    private static final Logger logger = LoggerFactory.getLogger(ObservationController.class);

    @PostMapping(value = "/sendObservationRequestSignal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "sendObservationRequestSignal")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token", required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Send Observation Request Signal OK")})
    public void sendObservationRequestSignal(KeycloakAuthenticationToken kat, @RequestBody ObservationRequestSignal observationRequestSignal) {

        KeycloakPrincipal keycloakPrincipal= (KeycloakPrincipal) kat.getPrincipal();
        AccessToken token= keycloakPrincipal.getKeycloakSecurityContext().getToken();

        String senderUsername= token.getPreferredUsername();

        observationRequestSignal.setObserverUsername(senderUsername);




        Message resultMessage =  MessageBuilder
                .withPayload(observationRequestSignal)
                .setHeader("action"
                        , "sendObservationRequestSignal")
                .build();
        source.output().send(resultMessage);



        logger.info("sendObservationRequestSignal", observationRequestSignal);

    }

}