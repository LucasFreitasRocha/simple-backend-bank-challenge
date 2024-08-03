package io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    private Long payer;
    private Long payee;
    private BigDecimal value;
}
