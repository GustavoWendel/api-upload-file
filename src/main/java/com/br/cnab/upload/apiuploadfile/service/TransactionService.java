package com.br.cnab.upload.apiuploadfile.service;

import com.br.cnab.upload.apiuploadfile.model.TransactionSummaryDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionSummaryDTO> getTransactionSummaryByStore();
}
