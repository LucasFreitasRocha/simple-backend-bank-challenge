package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserTypeDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.TransferEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.UserMapper.*;


@Component
@AllArgsConstructor
public class TransferMapper  {

    public  List<TransferDomain> toDomainList(List<TransferEntity> entities) {
            return null;
    }
    public  TransferEntity toEntity(TransferDomain domain) {
            return null;
    }

    public  List<TransferEntity> toEntityList(List<TransferDomain> domains) {
            return null;
    }












}
