package io.github.lucasfreitasrocha.simple_bank.controller;

import io.github.lucasfreitasrocha.simple_bank.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simple_bank.dto.out.CreatedUserDto;
import io.github.lucasfreitasrocha.simple_bank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<CreatedUserDto> create(@RequestBody CreateUserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
 }
