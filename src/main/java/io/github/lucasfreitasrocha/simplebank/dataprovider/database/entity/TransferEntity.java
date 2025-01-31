package io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity;

import io.github.lucasfreitasrocha.simplebank.core.domain.StatusPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

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
    @JoinColumn(name = "id_payer")
    private AccountEntity payer;
    @ManyToOne
    @JoinColumn(name = "id_payee")
    private AccountEntity payee;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
    private UUID idTransaction;

}
