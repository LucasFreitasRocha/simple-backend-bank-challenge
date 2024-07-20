package io.github.lucasfreitasrocha.simple_bank.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUserDto {
    private Long id;
    private String name;
    private String document;
    private String email;
}
