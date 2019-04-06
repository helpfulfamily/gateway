package army.helpful.prosoha.message.publisher;


import army.helpful.prosoha.actions.EnumActionStatus;
import army.helpful.prosoha.message.RestClient;
import army.helpful.prosoha.message.model.*;
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
    public Message publishContent(@RequestBody ProblemContent message)
    {


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

    @GetMapping(value = "/all/{amount}")
    public List<ProblemTitle> getAll(@PathVariable int amount) {
        List<ProblemTitle> titleList= null;

        ProblemTitleMessage message= (ProblemTitleMessage) restClient.getForEntity("/problemtitle/all/"+ amount,
                ProblemTitleMessage.class);

        titleList= message.getProblemTitleList();

        return titleList;
    }


    @GetMapping(value = "/contents/{name}/{amount}")
    public List<ProblemContent> getContentsByTitle(@PathVariable String name, @PathVariable int amount) {
        List<ProblemContent> contentList= null;

        ProblemContentMessage message= (ProblemContentMessage) restClient.getForEntity("/problemtitle/contents/"+name+"/"+amount
                , ProblemContentMessage.class);

        contentList= message.getProblemContentList();
        return contentList;
    }

}