package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;


public interface InitTranferGateway {

    public TransferDomain initTrasnfer(TransferDomain transferDomain);
}
