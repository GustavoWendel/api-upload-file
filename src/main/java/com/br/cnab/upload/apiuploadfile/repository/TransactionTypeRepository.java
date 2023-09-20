package com.br.cnab.upload.apiuploadfile.repository;

import com.br.cnab.upload.apiuploadfile.model.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
