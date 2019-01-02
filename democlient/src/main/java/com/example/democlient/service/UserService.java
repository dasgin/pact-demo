package com.example.democlient.service;

import com.example.democlient.domain.User;
import com.example.democlient.model.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User fetchUser(Long userId) {
        return restTemplate.getForObject("/operators/{userId}", User.class, userId);
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        restTemplate.postForEntity("/operators", userCreateRequest, Void.class);
    }
}
