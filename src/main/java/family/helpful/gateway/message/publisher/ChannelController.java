package family.helpful.gateway.message.publisher;


import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.*;
import family.helpful.gateway.util.KeycloakUtil;
import io.swagger.annotations.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@EnableBinding(Source.class)
@RestController
@RequestMapping(value = "/channel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChannelController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @GetMapping(value = "/{channelName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Channel getChannel(@PathVariable String channelName) {
        Channel channel = (Channel) restClient.getForEntity("/channel/"+channelName,   Channel.class);

        return channel;
    }


    @GetMapping(value = "/all/{amount}")
    public List<Channel> getAll(@PathVariable int amount) {
        List<Channel> titleList= null;

        ChannelMessage message= (ChannelMessage) restClient.getForEntity("/channel/all/"+ amount,
                ChannelMessage.class);

        titleList= message.getChannelList();

        return titleList;
    }

    @PostMapping("/publishChannelContent")
    @ApiOperation(value = "Publish Content")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token",
            required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Publish Content OK")})
    public Message publishContent(KeycloakAuthenticationToken kat, @RequestBody ChannelContent channelContent)
    {

        // In order to get user via Keycloak

        User user= new User();
        user.setUsername(KeycloakUtil.getUserName(kat));

        channelContent.setUser(user);

        // Prepare Message envelope to use it in the Persist side.
        Message resultMessage =  MessageBuilder
                .withPayload(channelContent)
                .setHeader("action"
                        , "publishChannelContent")
                .build();

        // Sends message to Persist side.
        source.output().send(resultMessage);


        // It is not directly related to current algorithm and just used to be informed about process.
        logger.info("Message sent to Persist side: ", resultMessage);

        return resultMessage;
    }
    @PostMapping("/createChannel")
    @ApiOperation(value = "Create Channel")
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", value = "JWT authorization token",
            required = true, dataType = "string", paramType = "header"))
    @ApiResponses({@ApiResponse(code = 200, message = "Create Channel OK")})
    public void createChannel(KeycloakAuthenticationToken kat, @RequestBody Channel channelObject)
    {
        // In order to get user via Keycloak

        User user= new User();
        user.setUsername(KeycloakUtil.getUserName(kat));

        // Sets user of Channel object envelope
        channelObject.setUser(user);

        // Prepare Message envelope to use it in the Persist side.
        Message  channelObjectMessage =  MessageBuilder
                .withPayload(channelObject)
                .setHeader("action"
                        , "createChannel")
                .build();

        // Sends message to Persist side.
        source.output().send(channelObjectMessage);

        // It is not directly related to current algorithm and just used to be informed about process.
        logger.info("Message sent to Persist side: ", channelObject);

    }

    @GetMapping(value = "/contents/{channelName}/{pageNumber}")
    public List<ChannelContent> getContentsByTitle(@PathVariable String channelName, @PathVariable int pageNumber) {
        List<ChannelContent> contents = null;
        try {
            channelName = URLEncoder.encode(channelName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ChannelContentMessage message = (ChannelContentMessage) restClient
                .getForEntity("/channel/contents/" + channelName + "/" + pageNumber
                        , ChannelContentMessage.class);

        contents = message.getContents();
        return contents;
    }
}