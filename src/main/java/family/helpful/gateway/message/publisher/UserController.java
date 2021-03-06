package family.helpful.gateway.message.publisher;


import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.User;
import family.helpful.gateway.message.model.UserJoinMessage;
import family.helpful.gateway.message.model.UserMessage;
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
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/changeProfilePhotoUrl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeProfilePhotoUrl(@RequestBody User user) {
        Message resultMessage =  MessageBuilder
                .withPayload(user)
                .setHeader("action"
                        , "changeProfilePhotoUrl")
                .build();
        source.output().send(resultMessage);



        logger.info("changeProfilePhotoUrl", user);

    }
    @PostMapping(value = "/changeCoverUrl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeCoverUrl(@RequestBody User user) {
        Message resultMessage =  MessageBuilder
                .withPayload(user)
                .setHeader("action"
                        , "changeCoverUrl")
                .build();
        source.output().send(resultMessage);



        logger.info("changeProfilePhotoUrl", user);

    }
    @GetMapping(value = "/userChannelJoinPart/{username}/{channelName}/{actionType}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void userChannelJoinPart(@PathVariable String username, @PathVariable String channelName
                                 , @PathVariable String actionType) {
        User user = (User) restClient.getForEntity("/user/"+username,   User.class);
        UserJoinMessage userJoinMessage = new UserJoinMessage();
        userJoinMessage.setUser(user);
        userJoinMessage.setChannelName(channelName);
        userJoinMessage.setActionType(actionType);

        Message resultMessage =  MessageBuilder
                .withPayload(userJoinMessage)
                .setHeader("action"
                        , "userChannelJoinPart")
                .build();
        source.output().send(resultMessage);
    }
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable String username) {
        User user = (User) restClient.getForEntity("/user/"+username,   User.class);
        return user;
    }
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createUser(@RequestBody User user) {

         restClient.post("/user/create",   user);

    }
    @GetMapping(value = "/all/{amount}/{channel}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserMessage getAll(@PathVariable int amount, @PathVariable String channel) {


        UserMessage userMessage=  (UserMessage) restClient
                      .getForEntity("/user/all/"+amount+"/"+channel,
                              UserMessage.class);


        return userMessage;
    }

}