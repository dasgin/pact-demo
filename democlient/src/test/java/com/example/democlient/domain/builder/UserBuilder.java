package com.example.democlient.domain.builder;

import com.example.democlient.domain.User;

public final class UserBuilder {
    private Long id;
    private String name;
    private String surname;
    private String role;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder role(String role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(role);
        return user;
    }
}
