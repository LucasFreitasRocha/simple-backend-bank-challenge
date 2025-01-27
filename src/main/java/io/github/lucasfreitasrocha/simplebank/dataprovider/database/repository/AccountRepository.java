package io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
