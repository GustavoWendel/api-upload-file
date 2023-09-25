package com.br.cnab.upload.apiuploadfile.repository;

import com.br.cnab.upload.apiuploadfile.entity.CNAB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnabRepository extends JpaRepository<CNAB, Long> {
}
