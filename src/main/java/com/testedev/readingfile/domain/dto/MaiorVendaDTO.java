package com.testedev.readingfile.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaiorVendaDTO {

    private Long idVenda;
    private BigDecimal valorTotal;
}
