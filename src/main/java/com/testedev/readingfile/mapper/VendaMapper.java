package com.testedev.readingfile.mapper;

import com.testedev.readingfile.domain.dto.ItemVendaDTO;
import com.testedev.readingfile.domain.dto.VendaDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class VendaMapper implements Function<String[], VendaDTO> {

    @Override
    public VendaDTO apply(String[] dados) {
        String[] itensVenda = dados[2].replace("[", "").replace("]", "").split(",");
        List<ItemVendaDTO> items = new ArrayList<ItemVendaDTO>();
        for (String item : itensVenda) {
            String[] detalheItem = item.split("-");
            items.add(ItemVendaDTO.builder().id(Long.parseLong(detalheItem[0]))
                    .quantidade(Long.parseLong(detalheItem[1]))
                    .preco(new BigDecimal(detalheItem[2])).build());
        }

        return VendaDTO.builder().id(Long.parseLong(dados[0]))
                .idVenda(Long.parseLong(dados[1]))
                .items(items)
                .vendedor(dados[3]).build();
    }
}
