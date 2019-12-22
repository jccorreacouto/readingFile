package com.testedev.readingfile.mapper;

import com.testedev.readingfile.domain.dto.ClienteDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClienteMapper implements Function<String[], ClienteDTO> {

    @Override
    public ClienteDTO apply(String[] dados) {
        return ClienteDTO.builder().id(Long.parseLong(dados[0]))
                .cnpj(dados[1])
                .nome(dados[2])
                .areaNegocio(dados[3])
                .build();
    }
}
