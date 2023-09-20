package com.br.cnab.upload.apiuploadfile.repository;

import com.br.cnab.upload.apiuploadfile.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
