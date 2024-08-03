package io.github.lucasfreitasrocha.simple_bank.core.gateway;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;

public interface TransferDbGateway {
    TransferDomain save(TransferDomain domain);
}
