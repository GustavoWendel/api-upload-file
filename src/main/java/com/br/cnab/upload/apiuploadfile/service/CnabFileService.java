package com.br.cnab.upload.apiuploadfile.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public interface CnabFileService {

    void readFile(InputStream is) throws IOException;

    List<Map<String, Object>> getAllStore(String storeName);

}
