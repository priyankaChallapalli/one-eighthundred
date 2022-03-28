package com.wipro.oneeighthundred.service;

import com.wipro.oneeighthundred.response.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private JSONFeedService jsonFeedService;

    @InjectMocks
    private UserServiceImpl userService;

    static List<User> users = new ArrayList<>();

    @BeforeAll
    public static void populateUserList(){
        users.add(new User(1,1,"test user 1","test user 1"));
        users.add(new User(2,2,"test user 2","test user 2"));
        users.add(new User(1,3,"test user 3","test user 3"));
        users.add(new User(4,4,"test user 4","test user 4"));
        users.add(new User(5,5,"test user 5","test user 5"));
    }

    @Test
    public void getDistinctCountTest() throws Exception{
        Mockito.when(jsonFeedService.getJSONFeedData()).thenReturn(users);
        Assertions.assertEquals(4L,userService.getDistinctCount().getCount());
    }

    @Test
    public void getDistinctCountTestWithException() throws Exception{
        Mockito.when(jsonFeedService.getJSONFeedData()).thenThrow(new Exception());
        Assertions.assertThrows(Exception.class,()->{userService.getDistinctCount();});
    }

    @Test
    public void getUserDetailsTest() throws Exception{
        Mockito.when(jsonFeedService.getJSONFeedData()).thenReturn(users);
        ReflectionTestUtils.setField(userService,"userBody","1800Flowers");
        ReflectionTestUtils.setField(userService,"userTitle","1800Flowers");
        Assertions.assertEquals("1800Flowers",userService.getUserDetails().getUserList().get(3).getBody());
        Assertions.assertEquals("1800Flowers", userService.getUserDetails().getUserList().get(3).getTitle());
    }

    @Test
    public void getUserDetailsTestWithException() throws Exception{
        Mockito.when(jsonFeedService.getJSONFeedData()).thenThrow(new Exception());
        Assertions.assertThrows(Exception.class,()->{userService.getUserDetails();});
    }
}
