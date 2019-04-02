package army.helpful.prosoha.message.publisher;


import army.helpful.prosoha.actions.EnumActionStatus;
import army.helpful.prosoha.actions.EnumActionTypes;
import army.helpful.prosoha.message.RestClient;
import army.helpful.prosoha.message.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@EnableBinding(Source.class)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProsoController
{
    @Autowired
    private Source source;

    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(ProsoController.class);

    @PostMapping("/title/{action}")
    public Message publishContent(@RequestBody Content message, @PathVariable String action)
    {


        Message resultMessage =  MessageBuilder
                .withPayload(message)
                .setHeader("action"
                        , action+message.getClass().getSimpleName())
                .build();
        source.output().send(resultMessage);

          resultMessage= MessageBuilder.withPayload(message)
                .setHeader(EnumActionTypes.publishContent.name()
                        , EnumActionStatus.SUCCESS.name()).build();

        logger.info("publishContent", message);

        return resultMessage;
    }

    @GetMapping(value = "/title/all/{amount}")
    public List<Title> getAll(@PathVariable int amount) {
        List<Title> titleList= null;

        TitleMessage message= (TitleMessage) restClient.getForEntity("/title/all/"+ amount,
                TitleMessage.class);

        titleList= message.getTitleList();

        return titleList;
    }


    @GetMapping(value = "/title/contents/{name}/{amount}")
    public List<Content> getContentsByTitle(@PathVariable String name, @PathVariable int amount) {
        List<Content> contentList= null;

        ContentMessage message= (ContentMessage) restClient.getForEntity("/title/contents/"+name+"/"+amount
                , ContentMessage.class);

        contentList= message.getContentList();
        return contentList;
    }

    @GetMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable String username) {
        User user = (User) restClient.getForEntity("/user/"+username,   User.class);
        return user;
    }
    @PostMapping(value = "/user/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createUser(@RequestBody User user) {

         restClient.post("/user/create",   user);

    }
}