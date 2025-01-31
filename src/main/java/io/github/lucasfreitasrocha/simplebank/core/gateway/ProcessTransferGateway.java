package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;

public interface ProcessTransferGateway {

    void process(TransferDomain domain, String idTransaction);
}
