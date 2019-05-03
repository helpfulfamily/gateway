package family.helpful.gateway.message.publisher;


import family.helpful.gateway.message.RestClient;
import family.helpful.gateway.message.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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



}