package io.github.lucasfreitasrocha.simple_bank.core.usecase;

import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BalanceValidator {


    public void unauthorized(HandlerErrorService handlerErrorService) {
        log.error("operação não autorizada");
        handlerErrorService.addFieldError("unauthorized", "operação não autorizada");
        handlerErrorService.addHttpStatus(HttpStatus.FORBIDDEN);
        handlerErrorService.handle();
    }

    public void insufficientBalance(HandlerErrorService handlerErrorService) {
        handlerErrorService.addFieldError("OperationNotPermit", "Saldo insuficiente");
    }
}
