package io.github.lucasfreitasrocha.simple_bank.controller;

import io.github.lucasfreitasrocha.simple_bank.dto.TransferDto;
import io.github.lucasfreitasrocha.simple_bank.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferController {

   private final TransferService service;

    @PostMapping
    public ResponseEntity<TransferDto> transferValue(@RequestBody TransferDto transferDto){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(service.transferValue(transferDto));
    }
}
