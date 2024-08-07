package io.github.lucasfreitasrocha.simple_bank.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {


    private Long id;
    private String name;
    private UserTypeDomain type;
    private String document;
    private String email;
    private String password;
    private AccountDomain account;

}
