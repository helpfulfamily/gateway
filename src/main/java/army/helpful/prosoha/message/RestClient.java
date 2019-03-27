package army.helpful.prosoha.message;


import army.helpful.prosoha.message.model.TitleMessage;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class RestClient {

    @Value("${PERSISTHA_URL}")
    private String PERSISTHA_URL;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestClient.class);


    public Object getForEntity(String path, Class <?> c)  {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        Object resultMessage = null;
        ResponseEntity<?> response = null;
        try {

            response = restTemplate.getForEntity(PERSISTHA_URL+path,  c);
            if(response != null){
                resultMessage= response.getBody();
            }

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            LOGGER.error("Error {} when calling this rest service:: {}"
                    , e.getRawStatusCode()
                    , path
                    ,"getForEntity");

        }
       return   resultMessage;
    }

}

