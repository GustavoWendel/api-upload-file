package com.br.cnab.upload.apiuploadfile.controller;

import com.br.cnab.upload.apiuploadfile.service.CnabFileService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;



import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CnabFileController {

    private final CnabFileService cnabFileService;

    private final TemplateEngine templateEngine;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            String errorMessage = "Empty file";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        cnabFileService.readFile(file.getInputStream());

        String successMessage = "File uploaded successfully.";
        return ResponseEntity.ok(successMessage);
    }

}
