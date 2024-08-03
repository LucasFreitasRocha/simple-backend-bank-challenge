package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity owner;
    private BigDecimal balance;


    public AccountEntity(UserEntity owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }
}
