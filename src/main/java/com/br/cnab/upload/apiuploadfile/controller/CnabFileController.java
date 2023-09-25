package com.br.cnab.upload.apiuploadfile.controller;

import com.br.cnab.upload.apiuploadfile.model.StoreOperation;
import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.service.CnabFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CnabFileController {

    private final CnabFileService cnabFileService;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            String errorMessage = "Empty file";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        cnabFileService.processCNABFile(file);

        String successMessage = "File uploaded successfully.";
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/store-operations")
    public String storeOperations(Model model) {
        List<StoreOperationResponse> storeOperationsList = cnabFileService.listStoreOperations();
        model.addAttribute("storeOperationsList", storeOperationsList);
        return "store-operations";
    }

}
