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
public class ItemVendaDTO {

    private Long id;
    private Long quantidade;
    private BigDecimal preco;
}
