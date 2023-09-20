package com.br.cnab.upload.apiuploadfile.service.impl;

import com.br.cnab.upload.apiuploadfile.model.entity.File;
import com.br.cnab.upload.apiuploadfile.repository.FileRepository;
import com.br.cnab.upload.apiuploadfile.service.CnabFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CnabFileServiceImpl implements CnabFileService {

    private List<Integer> appetizer = Arrays.asList(1, 4, 5, 6, 7, 8);

    private final FileRepository fileRepository;

    public void readFile(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (reader.ready()) {
            fileRepository.save(parseFile(reader.readLine()));
        }
    }

    public List<Map<String, Object>> getAll() {
        Iterable<File> files = fileRepository.findAll(Sort.by("storeName"));
        return transactionsGroup(files);
    }

    private List<Map<String, Object>> transactionsGroup(Iterable<File> files) {
        List<Map<String, Object>> stores = new ArrayList<>();
        List<Map<String, Object>> transactions = new ArrayList<>();
        Map<String, Object> transaction;
        String storeName = "";
        Map<String, Object> store = new HashMap<>();
        BigDecimal balance = BigDecimal.ZERO;
        boolean hasStore = false;

        for (File file : files) {
            hasStore = true;

            if (!storeName.equalsIgnoreCase(file.getStoreName())) {
                if (!storeName.isEmpty()) {
                    store.put("account_balance", balance);
                    store.put("transactions", transactions);
                    stores.add(store);
                    transactions = new ArrayList<>();
                }

                storeName = file.getStoreName();
                store = new HashMap<>();
                store.put("storeName", storeName);
                store.put("cpf", file.getCpf());
                store.put("storeOwner", file.getStoreOwner());
                balance = BigDecimal.ZERO;
            }

            transaction = new HashMap<>();
            transaction.put("type", getTransaction(file.getType()));
            transaction.put("operation", getTransaction(file.getType()));

            BigDecimal transactionValue = file.getValue();
            if (appetizer.contains(file.getType())) {
                transaction.put("value", transactionValue);
                transaction.put("date", file.getDate());
                transactions.add(transaction);
                balance = balance.add(transactionValue);
            } else {
                transaction.put("value", transactionValue);
                transaction.put("date", file.getDate());
                transactions.add(transaction);
                balance = balance.subtract(transactionValue);
            }
        }

        if (hasStore) {
            store.put("account_balance", balance);
            store.put("transactions", transactions);
            stores.add(store);
        }

        return stores;
    }

    private String getTransaction(Integer type) {
        return switch (type) {
            case 1 -> "debt";
            case 2 -> "payment slip";
            case 3 -> "Financing";
            case 4 -> "Credit";
            case 5 -> "Loan Receipt";
            case 6 -> "Sales";
            case 7 -> "TED receipt";
            case 8 -> "DOC receipt";
            default -> "Rent";
        };
    }

    private File parseFile(String line) {
        String card = line.substring(30, 42);
        String hour = line.substring(42, 48);
        String owner = line.substring(48, 62);
        String store = line.substring(62).trim();
        Integer type = Integer.parseInt(line.substring(0, 1));
        String date = line.substring(1, 9);
        BigDecimal value = parseValue(line.substring(9, 19));
        String cpf = line.substring(19, 30);

        LocalDateTime dateTime = LocalDateTime.now()
                .withYear(Integer.parseInt(date.substring(0, 4)))
                .withMonth(Integer.parseInt(date.substring(4, 6)))
                .withDayOfMonth(Integer.parseInt(date.substring(6, 8)))
                .withHour(Integer.parseInt(hour.substring(0, 2)))
                .withMinute(Integer.parseInt(hour.substring(2, 4)))
                .withSecond(Integer.parseInt(hour.substring(4, 6)));

        return File.builder()
                .type(type)
                .date(dateTime.toLocalDate())
                .value(value)
                .cpf(cpf)
                .card(card)
                .storeOwner(owner)
                .storeName(store)
                .build();
    }

    public List<Map<String, Object>> getAllStore(String storeName) {
        Iterable<File> files = fileRepository.findByStoreNameContainingIgnoreCase(storeName);
        return transactionsGroup(files);
    }

    private BigDecimal parseValue(String valueString) {
        int intValue = Integer.parseInt(valueString);
        return BigDecimal.valueOf(intValue).divide(BigDecimal.valueOf(100));
    }
}




