package io.github.lucasfreitasrocha.simple_bank.dataprovider.client.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthDto {
    private String status;
    private DataResponseAuthDto data;
}
