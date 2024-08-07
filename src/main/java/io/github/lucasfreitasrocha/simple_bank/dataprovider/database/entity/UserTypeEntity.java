package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity;


import lombok.Getter;

@Getter
public enum UserTypeEntity {
    PF("PF"),
    PJ("PJ");

    private String name;

    UserTypeEntity(String type) {
    }

    public static UserTypeEntity getFromName(String name) {
        for (UserTypeEntity userTypeEntity : values()) {
            if (userTypeEntity.toString().equals(name)) {
                return userTypeEntity;
            }
        }
        throw new RuntimeException("UserTypeNotFound");
    }
}
