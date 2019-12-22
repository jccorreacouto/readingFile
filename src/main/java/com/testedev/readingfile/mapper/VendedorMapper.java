package com.testedev.readingfile.mapper;

import com.testedev.readingfile.domain.dto.VendedorDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;

@Component
public class VendedorMapper implements Function<String[], VendedorDTO> {

    @Override
    public VendedorDTO apply(String[] dados) {
        return VendedorDTO.builder().id(Long.parseLong(dados[0]))
                                    .cpf(dados[1])
                                    .nome(dados[2])
                                    .salario(new BigDecimal(dados[3]))
                                    .build();
    }
}
