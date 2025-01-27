package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;

public interface TransferDbGateway {
    TransferDomain save(TransferDomain domain);
}
