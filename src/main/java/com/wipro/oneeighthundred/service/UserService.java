package com.wipro.oneeighthundred.service;

import com.wipro.oneeighthundred.response.CountResponse;
import com.wipro.oneeighthundred.response.UsersResponse;

//TODO : Rename to something meaningful
public interface UserService {
    CountResponse getDistinctCount() throws Exception;

    UsersResponse getUserDetails() throws Exception;
}
