package io.github.lucasfreitasrocha.simple_bank.repository;

import io.github.lucasfreitasrocha.simple_bank.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
