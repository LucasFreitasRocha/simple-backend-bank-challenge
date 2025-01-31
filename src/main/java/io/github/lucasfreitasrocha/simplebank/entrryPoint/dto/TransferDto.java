package io.github.lucasfreitasrocha.simplebank.entrryPoint.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDto {
    private String idTransaction;
    private Long payer;
    private Long payee;
    private BigDecimal value;
}
