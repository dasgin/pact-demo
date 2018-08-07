package com.example.democlient.model.request;

public class UserFilterRequest {

    private String role;

    public UserFilterRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
