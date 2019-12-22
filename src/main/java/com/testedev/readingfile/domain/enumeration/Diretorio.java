package com.testedev.readingfile.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Diretorio {

    DATA("data"),
    IN("in"),
    OUT("out");

    private String diretorio;

}
