package com.testedev.readingfile.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@AllArgsConstructor
public enum Diretorio {

    @Value("${spring.application.diretorio.data}")
    DATA,

    @Value("${spring.application.diretorio.in}")
    IN,

    @Value("${spring.application.diretorio.out}")
    OUT;

}
