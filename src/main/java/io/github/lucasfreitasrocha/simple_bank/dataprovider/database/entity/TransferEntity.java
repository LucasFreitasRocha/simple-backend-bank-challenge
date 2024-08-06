package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_transfer")
public class TransferEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn( name = "id_payer")
    private AccountEntity payer;
    @ManyToOne
    @JoinColumn( name = "id_payee")
    private AccountEntity payee;
    private BigDecimal value;

}
