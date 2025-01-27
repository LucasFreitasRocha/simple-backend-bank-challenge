package io.github.lucasfreitasrocha.simplebank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simplebank.core.domain.StatusPayment;
import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper.CycleAvoidingMappingContext;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper.TransferMapperDb;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransferDbImpl implements TransferDbGateway {

    private final TransferRepository repository;
    private final TransferMapperDb mapper;
    private final CycleAvoidingMappingContext context;

    @Override
    public TransferDomain save(TransferDomain domain) {
        domain.setStatus(StatusPayment.PROCESSING);
        return mapper.toDomain(this.repository.save(mapper.toEntity(domain, context)), context);
    }


}
