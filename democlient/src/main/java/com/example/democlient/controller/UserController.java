package com.example.democlient.controller;

import com.example.democlient.domain.User;
import com.example.democlient.service.UserFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserFetcherService userFetcherService;

    public UserController(UserFetcherService userFetcherService) {
        this.userFetcherService = userFetcherService;
    }

    @GetMapping("/{userId}")
    public User fetchUserBy(@PathVariable Long userId) {
        return userFetcherService.fetchUser(userId);
    }
}
