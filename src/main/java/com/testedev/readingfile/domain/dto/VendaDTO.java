package com.testedev.readingfile.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {

    private Long id;
    private Long idVenda;
    private List<ItemVendaDTO> items;
    private String vendedor;
}
