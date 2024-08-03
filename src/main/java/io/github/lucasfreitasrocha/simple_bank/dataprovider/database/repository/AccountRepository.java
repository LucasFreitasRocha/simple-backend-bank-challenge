package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
