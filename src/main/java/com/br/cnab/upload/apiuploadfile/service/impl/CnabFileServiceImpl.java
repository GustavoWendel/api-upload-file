package com.br.cnab.upload.apiuploadfile.service.impl;

import com.br.cnab.upload.apiuploadfile.entity.CNAB;
import com.br.cnab.upload.apiuploadfile.model.StoreOperation;
import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.repository.CnabRepository;
import com.br.cnab.upload.apiuploadfile.service.CnabFileService;
import com.br.cnab.upload.apiuploadfile.utils.DateTimeUtils;
import com.br.cnab.upload.apiuploadfile.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CnabFileServiceImpl implements CnabFileService {

    private final CnabRepository cnabRepository;

    public void processCNABFile(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                CNAB cnab = parseFile(line);
                cnabRepository.save(cnab);
            }
        }
    }

    public List<StoreOperationResponse> listStoreOperations() {
        List<StoreOperationResponse> storeOperationsList = new ArrayList<>();
        List<CNAB> allRecords = cnabRepository.findAll();

        Map<String, StoreOperation> storeMap = new HashMap<>();

        for (CNAB cnab : allRecords) {
            String storeName = cnab.getStoreName().trim();

            if (!storeMap.containsKey(storeName)) {
                StoreOperation storeOperations = new StoreOperation(storeName);
                storeMap.put(storeName, storeOperations);
            }

            StoreOperation storeOperations = storeMap.get(storeName);
            storeOperations.addOperation(cnab);
        }

        for (StoreOperation storeOperation : storeMap.values()) {
            storeOperationsList.add(storeOperation.toResponse());
        }

        return storeOperationsList;
    }

    private CNAB parseFile(String line) {
        String card = line.substring(30, 42);
        String hour = line.substring(42, 48);
        String owner = line.substring(48, 62);
        String storeName = line.substring(62).trim();
        Integer type = Integer.parseInt(line.substring(0, 1));
        String date = line.substring(1, 9);
        double value = Double.parseDouble(line.substring(9, 19)) / 100;
        String cpf = line.substring(19, 30);

        CNAB cnab = new CNAB();

        cnab.setTransactionType(type);
        cnab.setTransactionDate(DateUtils.formatDateToYYYYMMDD(date));
        cnab.setTransactionValue(value);
        cnab.setCpf(cpf);
        cnab.setCard(card);
        cnab.setOccurrenceTime(DateTimeUtils.formatTimeInUTC3(hour));
        cnab.setStoreOwner(owner);
        cnab.setStoreName(storeName);

        return cnab;
    }

}




