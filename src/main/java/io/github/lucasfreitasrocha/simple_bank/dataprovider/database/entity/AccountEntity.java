package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.util.List;

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
    @OneToMany(mappedBy = "payer")
    private List<TransferEntity> payments;
    @OneToMany(mappedBy = "payee")
    private List<TransferEntity> receipts;

    public AccountEntity(UserEntity owner) {
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
    }
}
