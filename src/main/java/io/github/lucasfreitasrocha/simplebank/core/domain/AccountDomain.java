package io.github.lucasfreitasrocha.simplebank.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDomain {

    private Long id;
    @JsonBackReference
    private UserDomain owner;
    private BigDecimal balance;
    private List<TransferDomain> payments;
    private List<TransferDomain> receipts;


    public AccountDomain(UserDomain owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }
}
