package io.github.lucasfreitasrocha.simplebank.entrryPoint.api.controller;

import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.in.BalanceDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.api.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/deposit/{id}")
    public ResponseEntity<Void> deposit(@PathVariable Long id, @RequestBody BalanceDto balanceDto) {
        service.deposit(id, balanceDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestBody BalanceDto balanceDto) {
        service.withdraw(id, balanceDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
