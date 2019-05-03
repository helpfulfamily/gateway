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

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;


@EnableBinding(Source.class)
@RestController
@RequestMapping(value = "/problemtitle")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProblemController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @PostMapping("/publishProblemContent")
    @ApiOperation(value = "Publish Content")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token", required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Publish Content OK")})
    public Message publishContent(KeycloakAuthenticationToken kat, @RequestBody ProblemContent message)
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
                        , "publishProblemContent")
                .build();
        source.output().send(resultMessage);

          resultMessage= MessageBuilder.withPayload(message)
                .setHeader("publishProblemContent"
                        , EnumActionStatus.SUCCESS.name()).build();

        logger.info("action", message);

        return resultMessage;
    }
    @GetMapping(value = "/all/{amount}/{channel}")
    public List<ProblemTitle> getAll(@PathVariable int amount,  @PathVariable String channel) {
        List<ProblemTitle> titleList= null;

        ProblemTitleMessage message= (ProblemTitleMessage) restClient
                .getForEntity("/problemtitle/all/"+ amount+"/"+ channel,
                ProblemTitleMessage.class);

        titleList= message.getProblemTitleList();

        return titleList;
    }

    @GetMapping(value = "/all/{amount}")
    public List<ProblemTitle> getAll(@PathVariable int amount) {
        List<ProblemTitle> titleList= null;

        ProblemTitleMessage message= (ProblemTitleMessage) restClient
                .getForEntity("/problemtitle/all/"+ amount,
                        ProblemTitleMessage.class);

        titleList= message.getProblemTitleList();

        return titleList;
    }

    @GetMapping(value = "/contents/{name}/{amount}")
    public List<ProblemContent> getContentsByTitle(@PathVariable String name, @PathVariable int amount) {
        List<ProblemContent> contentList= null;
      try {
            name =URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProblemContentMessage message= (ProblemContentMessage) restClient.getForEntity("/problemtitle/contents/"+name+"/"+amount
                , ProblemContentMessage.class);

        contentList= message.getProblemContentList();
        return contentList;
    }

}