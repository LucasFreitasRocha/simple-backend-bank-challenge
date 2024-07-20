package io.github.lucasfreitasrocha.simple_bank.model;

import lombok.Getter;

@Getter
public enum UserType {
    PF("PF"),
    PJ("PJ");

    UserType(String type) {
    }

    private String name;
}
