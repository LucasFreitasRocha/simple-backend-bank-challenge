package io.github.lucasfreitasrocha.simple_bank.core.domain;

import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDomain {

    private Long id;
    private UserDomain owner;
    private BigDecimal balance;


    public AccountDomain(UserDomain owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }
}
