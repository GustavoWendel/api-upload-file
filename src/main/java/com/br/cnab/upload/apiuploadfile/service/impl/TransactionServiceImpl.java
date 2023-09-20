package com.br.cnab.upload.apiuploadfile.service.impl;

import com.br.cnab.upload.apiuploadfile.model.TransactionSummaryDTO;
import com.br.cnab.upload.apiuploadfile.repository.StoreRepository;
import com.br.cnab.upload.apiuploadfile.repository.TransactionRepository;
import com.br.cnab.upload.apiuploadfile.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<TransactionSummaryDTO> getTransactionSummaryByStore() {
        return Collections.emptyList();
    }

}

