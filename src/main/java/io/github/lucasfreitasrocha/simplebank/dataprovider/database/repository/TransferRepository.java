package io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
