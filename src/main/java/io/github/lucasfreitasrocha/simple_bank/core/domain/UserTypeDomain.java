package io.github.lucasfreitasrocha.simple_bank.core.domain;

import lombok.Getter;

@Getter
public enum UserTypeDomain {
    PF("PF"),
    PJ("PJ");

    private String name;

    UserTypeDomain(String type) {
    }
}
