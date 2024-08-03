package io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String document;
    private String email;
    private List<BigDecimal> payments;
    private List<BigDecimal> receipts;
}
