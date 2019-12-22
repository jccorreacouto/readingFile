package com.testedev.readingfile.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {

    private Long id;
    private String cpf;
    private String nome;
    private BigDecimal salario;

}
