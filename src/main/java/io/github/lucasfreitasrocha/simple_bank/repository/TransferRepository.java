package io.github.lucasfreitasrocha.simple_bank.repository;

import io.github.lucasfreitasrocha.simple_bank.model.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferModel, Long> {
}
