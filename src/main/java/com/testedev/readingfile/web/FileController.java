package com.testedev.readingfile.web;

import com.testedev.readingfile.response.ExtratoResponse;
import com.testedev.readingfile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
class FileController implements FileApi {

    @Autowired
    private FileService service;

    @Override
    @GetMapping("/vendas/extrato")
    public ResponseEntity<ExtratoResponse> obterExtratoVendas() {
        return ResponseEntity.ok(this.service.obterExtratoVendas());
    }
}
