package com.testedev.readingfile.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoResponse {

    private Long qtdeClientes;
    private Long qtdeVendedores;
    private Long idMaiorVenda;
    private String piorVendedor;
}
