package io.github.lucasfreitasrocha.simple_bank.core.domain;

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
    private UserDomain payer;
    private UserDomain payee;
    private BigDecimal value;
}
