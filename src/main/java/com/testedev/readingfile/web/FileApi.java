package com.testedev.readingfile.web;

import com.testedev.readingfile.response.ExtratoResponse;
import org.springframework.http.ResponseEntity;

public interface FileApi {

    ResponseEntity<ExtratoResponse> obterExtratoVendas();

}
