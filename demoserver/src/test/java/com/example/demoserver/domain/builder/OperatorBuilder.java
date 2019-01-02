package com.example.demoserver.domain.builder;

import com.example.demoserver.domain.Operator;

public final class OperatorBuilder {
    private Long id;
    private String name;
    private String surname;
    private String role;

    private OperatorBuilder() {
    }

    public static OperatorBuilder anOperator() {
        return new OperatorBuilder();
    }

    public OperatorBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public OperatorBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OperatorBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public OperatorBuilder role(String role) {
        this.role = role;
        return this;
    }

    public Operator build() {
        Operator operator = new Operator();
        operator.setId(id);
        operator.setName(name);
        operator.setSurname(surname);
        operator.setRole(role);
        return operator;
    }
}
