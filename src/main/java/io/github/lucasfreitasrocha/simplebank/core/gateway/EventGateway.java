package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;

public interface EventGateway {
  void processTransfer(TransferDomain domain);
}
