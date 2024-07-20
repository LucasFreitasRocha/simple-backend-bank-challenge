package io.github.lucasfreitasrocha.simple_bank.dto.in;

import io.github.lucasfreitasrocha.simple_bank.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String name;
    private String document;
    private String email;
    private String password;
    private String type;
}
