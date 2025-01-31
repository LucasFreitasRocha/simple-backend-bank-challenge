package io.github.lucasfreitasrocha.simplebank.core.validator;

import io.github.lucasfreitasrocha.simplebank.core.exception.HandlerErrorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class CommonValidator {

    private final HandlerErrorService handlerErrorService;
    public void unauthorized() {
        handlerErrorService.init();
        log.error("operação não autorizada");
        handlerErrorService.addFieldError("unauthorized", "operação não autorizada");
        handlerErrorService.addHttpStatus(HttpStatus.FORBIDDEN);
        handlerErrorService.handle();
    }

    public void insufficientBalance(HandlerErrorService handlerErrorService) {
        handlerErrorService.addFieldError("OperationNotPermit", "Saldo insuficiente");
    }
}
