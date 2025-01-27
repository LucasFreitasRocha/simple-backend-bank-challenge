package io.github.lucasfreitasrocha.simplebank.core.domain;

import lombok.Getter;

@Getter
public enum UserTypeDomain {
    PF("PF"),
    PJ("PJ");

    private String name;

    UserTypeDomain(String type) {
    }
}
