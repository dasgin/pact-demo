package com.example.demoserver.model.request;

public class OperatorFilterRequest {

    private String role;

    public OperatorFilterRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
