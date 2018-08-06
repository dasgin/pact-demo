package com.example.democlient.service;

import com.example.democlient.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserFetcherService {

    @Value("${demo.server.path}")
    private String serverPath;

    private final RestTemplate restTemplate;

    public UserFetcherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User fetchUser(Long userId) {
        return restTemplate.getForObject(serverPath +"/operators/{userId}", User.class, userId);
    }
}
