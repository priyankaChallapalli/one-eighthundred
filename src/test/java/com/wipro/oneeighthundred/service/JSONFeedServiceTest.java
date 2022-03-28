package com.wipro.oneeighthundred.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.oneeighthundred.response.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class JSONFeedServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private JSONFeedServiceImpl jsonFeedService;

    @Test
    public void getJSONFeedDataTest() throws Exception {
        Path path = Paths.get("src","test","resources","mockJsonFeed.json");
        List<User> users = new ObjectMapper().readValue(new String(Files.readAllBytes(path)),
                new TypeReference<List<User>>() {});

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        ReflectionTestUtils.setField(jsonFeedService,"JSONFeedURL","dummyURL");
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<User>>> any())).thenReturn(responseEntity);

        Assertions.assertEquals(100,jsonFeedService.getJSONFeedData().size());
    }

    @Test
    public void getJSONFeedDataTestWithException() throws Exception {
        ReflectionTestUtils.setField(jsonFeedService,"JSONFeedURL","dummyURL");
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<User>>> any())).thenThrow(RuntimeException.class);
        Assertions.assertThrows(Exception.class,()->{jsonFeedService.getJSONFeedData();});

    }
    @Test
    public void getJSONFeedDataTestWithInternalServerError() throws Exception {


        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ReflectionTestUtils.setField(jsonFeedService,"JSONFeedURL","dummyURL");
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<User>>> any())).thenReturn(responseEntity);

        Assertions.assertThrows(Exception.class,()->{jsonFeedService.getJSONFeedData();});

    }

}
