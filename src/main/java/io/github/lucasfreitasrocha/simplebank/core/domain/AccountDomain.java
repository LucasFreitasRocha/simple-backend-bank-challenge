package io.github.lucasfreitasrocha.simplebank.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDomain {

    private Long id;
    @JsonBackReference (value = "owner")
    private UserDomain owner;
    private BigDecimal balance;
    @JsonBackReference (value = "transferDomain")
    private List<TransferDomain> payments;
    @JsonBackReference (value = "transferDomain")
    private List<TransferDomain> receipts;


    public AccountDomain(UserDomain owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }
}
