package com.example.democlient.builder;

import com.example.democlient.model.request.UserCreateRequest;

public final class UserCreateRequestBuilder {
    private String name;
    private String surname;
    private String role;

    private UserCreateRequestBuilder() {
    }

    public static UserCreateRequestBuilder anUserCreateRequest() {
        return new UserCreateRequestBuilder();
    }

    public UserCreateRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserCreateRequestBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserCreateRequestBuilder role(String role) {
        this.role = role;
        return this;
    }

    public UserCreateRequest build() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setName(name);
        userCreateRequest.setSurname(surname);
        userCreateRequest.setRole(role);
        return userCreateRequest;
    }
}
