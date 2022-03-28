package com.wipro.oneeighthundred.service;

import com.wipro.oneeighthundred.response.User;

import java.util.List;

public interface JSONFeedService {

    List<User> getJSONFeedData() throws Exception;

}
