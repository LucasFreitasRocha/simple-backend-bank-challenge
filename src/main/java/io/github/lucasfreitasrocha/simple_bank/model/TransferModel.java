package io.github.lucasfreitasrocha.simple_bank.model;

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
public class TransferModel {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn( name = "id_payer")
    private UserModel payer;
    @ManyToOne
    @JoinColumn( name = "id_payee")
    private UserModel payee;
    private BigDecimal value;
}
