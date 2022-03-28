package com.wipro.oneeighthundred.controller;

import com.wipro.oneeighthundred.response.CountResponse;
import com.wipro.oneeighthundred.response.User;
import com.wipro.oneeighthundred.response.UsersResponse;
import com.wipro.oneeighthundred.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void distinctUserCountTest() throws Exception {
        CountResponse countResponse = new CountResponse(4L);
        Mockito.when(userService.getDistinctCount()).thenReturn(countResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/count"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(4L));
    }

    @Test
    public void distinctUserCountTestWithException() throws Exception {
        Mockito.when(userService.getDistinctCount()).thenThrow(new Exception(""));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/count"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void getUserDetailsTest() throws Exception {
        UsersResponse usersResponse = new UsersResponse();
        List<User> users = new ArrayList<>();
        users.add(new User(1,1,"test user 1","test user 1"));
        users.add(new User(2,2,"test user 2","test user 2"));
        usersResponse.setUserList(users);
        Mockito.when(userService.getUserDetails()).thenReturn(usersResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[0].userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[0].title").value("test user 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[0].body").value("test user 1"));
    }

    @Test
    public void getUserDetailsTestWithException() throws Exception {
        Mockito.when(userService.getUserDetails()).thenThrow(new Exception(""));
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
