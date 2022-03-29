package com.wipro.oneeighthundred.controller;

import com.wipro.oneeighthundred.response.CountResponse;
import com.wipro.oneeighthundred.response.UsersResponse;
import com.wipro.oneeighthundred.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/count")
    public ResponseEntity<CountResponse> distinctUserCount(){
        log.info("In distinctUserCount");
        try{
            return new ResponseEntity<>(userService.getDistinctCount(),HttpStatus.OK);
        }catch(Exception e){
            log.error("Error occurred while getting Distinct count {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UsersResponse> getUserDetails(){
        log.info("In getJsonFeedResponse");
        try{
            return new ResponseEntity<>(userService.getUserDetails(),HttpStatus.OK);
        }catch(Exception e){
            log.error("Error occurred while getting Distinct count {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
