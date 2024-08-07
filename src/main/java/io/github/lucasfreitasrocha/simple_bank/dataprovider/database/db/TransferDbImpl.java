package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.CycleAvoidingMappingContext;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.TransferMapperDb;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransferDbImpl implements TransferDbGateway {

    private final TransferRepository repository;
    private final TransferMapperDb mapper;
    private final CycleAvoidingMappingContext context;
    private final AccountDbImpl accountDb;

    @Override
    public TransferDomain save(TransferDomain domain) {
        accountDb.save(domain.getPayer());
        accountDb.save(domain.getPayee());
        return mapper.toDomain(this.repository.save(mapper.toEntity(domain, context)), context);
    }


}
