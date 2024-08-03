package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
