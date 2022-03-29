package com.wipro.oneeighthundred.service;

import com.wipro.oneeighthundred.response.CountResponse;
import com.wipro.oneeighthundred.response.User;
import com.wipro.oneeighthundred.response.UsersResponse;
import com.wipro.oneeighthundred.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JSONFeedService jsonFeedService;

    @Value("${user.body.1800Flowers:1800Flowers}")
    private String userBody;

    @Value("${user.title.1800Flowers:1800Flowers}")
    private String userTitle;

    @Override
    public CountResponse getDistinctCount() throws Exception {
        try{
            log.info("In UserServiceImpl.getDistinctCount()");
            List<User> jsonFeedData = jsonFeedService.getJSONFeedData();
            log.debug("jsonFeedData received :  {} ", jsonFeedData);
            //return new CountResponse(jsonFeedData.stream().distinct().count());
            return new CountResponse(jsonFeedData.stream().filter(Utility.distinctByKey(User::getUserId)).count());
        }catch (Exception e){
            log.error("Exception Occurred in UserServiceImpl.getDistinctCount() : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public UsersResponse getUserDetails() throws Exception {
        try{
            List<User> userList = jsonFeedService.getJSONFeedData();
            log.debug("jsonFeedDataList received :  {} ", userList);
            User user = userList.get(3);
            user.setBody(userBody);
            user.setTitle(userTitle);
            log.info("jsonFeedData after updating the values :  {} ", user);
            return new UsersResponse(userList);
        }catch (Exception e){
            log.error("Exception Occurred in UserServiceImpl.getJsonFeedResponse() : {}", e.getMessage());
            throw e;
        }
    }
}
