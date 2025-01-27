package io.github.lucasfreitasrocha.simplebank.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDomain {
    private Long id;
    private AccountDomain payer;
    private AccountDomain payee;
    private BigDecimal value;
    private StatusPayment status;
}
