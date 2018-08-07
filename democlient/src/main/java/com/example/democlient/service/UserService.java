package com.example.democlient.service;

import com.example.democlient.domain.User;
import com.example.democlient.model.request.UserFilterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Value("${demo.server.path}")
    private String serverPath;

    private final RestTemplate restTemplate;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User fetchUser(Long userId) {
        return restTemplate.getForObject(serverPath + "/operators/{userId}", User.class, userId);
    }

    public List<User> filterUsers(UserFilterRequest userFilterRequest) {
        UriComponents build = UriComponentsBuilder.fromPath(serverPath).path("operators").queryParam("role", userFilterRequest.getRole()).build();
        User[] users = restTemplate.getForEntity(build.toString(), User[].class).getBody();
        return Arrays.asList(users);
    }

    public void createUser(User user) {
        restTemplate.postForEntity(serverPath + "/operators", user, Void.class);
    }
}
