package com.example.democlient.service;

import com.example.democlient.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserFetcherService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserFetcherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User fetchUser(Long userId) {
        return restTemplate.getForObject("/operators/{userId}", User.class, userId);
    }
}
