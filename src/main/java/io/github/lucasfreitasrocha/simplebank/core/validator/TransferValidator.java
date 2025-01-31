package io.github.lucasfreitasrocha.simplebank.core.validator;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simplebank.core.gateway.TransferDbGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransferValidator {
    private  final TransferDbGateway transferDbGateway;
    private final HandlerErrorService handlerErrorService;
    private final CommonValidator commonValidator;
    public TransferDomain validateIntegrationTransfer(TransferDomain domain, String idTransaction){
        TransferDomain validation = transferDbGateway.findByIdTransaction(idTransaction);
        if(!domain.getPayer().equals(validation.getPayer())){
            commonValidator.unauthorized();
        }
        if(!domain.getPayee().equals(validation.getPayee())){
            commonValidator.unauthorized();
        }
        if(!domain.getValue().equals(domain.getValue())){
            commonValidator.unauthorized();
        }
        return validation;
    }
}
