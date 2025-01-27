package io.github.lucasfreitasrocha.simplebank.entrryPoint.api.controller;

import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.out.CreatedUserDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.out.UserDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService service;

    @PostMapping
    public ResponseEntity<CreatedUserDto> create(@RequestBody CreateUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }
}
