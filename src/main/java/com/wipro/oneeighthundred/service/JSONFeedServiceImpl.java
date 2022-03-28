package com.wipro.oneeighthundred.service;

import com.wipro.oneeighthundred.response.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class JSONFeedServiceImpl implements JSONFeedService{

    @Autowired
    RestTemplate restTemplate;

    @Value("${json.feed.url}")
    private String JSONFeedURL;

    @Override
    public List<User> getJSONFeedData() throws Exception {
        try{
            log.info("JSONFeed URL : {} ", JSONFeedURL);
            ResponseEntity<List<User>> responseEntity = restTemplate.exchange(JSONFeedURL,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                    });
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                log.info("Data fetched successfully from feed");
                return responseEntity.getBody();
            }else{
                log.error("Failed to get data from the feed : {} ", responseEntity.getStatusCode());
                throw new Exception("Exception occurred while fetching json data from feed");
            }
        }catch(Exception e){
            log.error("Exception occurred while fetching json data from feed : {} ", e.getMessage());
            throw e;
        }
    }


}
