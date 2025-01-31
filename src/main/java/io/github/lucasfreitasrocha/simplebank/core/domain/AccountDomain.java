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
@EqualsAndHashCode(of = "id")
public class AccountDomain {

    private Long id;
    @JsonBackReference (value = "owner")
    private UserDomain owner;
    private BigDecimal balance;



    public AccountDomain(UserDomain owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "AccountDomain{" +
                "id=" + id +
                ", owner=" + owner +
                ", balance=" + balance +
                '}';
    }
}
