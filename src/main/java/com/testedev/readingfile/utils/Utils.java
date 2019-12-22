package com.testedev.readingfile.utils;

import com.testedev.readingfile.domain.enumeration.Diretorio;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Utils {

    private static final String BARRA = File.separator;
    private static final String DIR_DATA = Diretorio.DATA.getDiretorio();
    private static final String DIR_IN = Diretorio.IN.getDiretorio();
    private static final String DIR_OUT = Diretorio.OUT.getDiretorio();

    public static String getDiretorioEntrata() {
        return System.getProperty("user.home").concat(BARRA).concat(DIR_DATA)
                                                .concat(BARRA).concat(DIR_IN);
    }

    public static File[] getArquivos(String dirEntrada) {
        return new File(dirEntrada).listFiles();
    }

    public static boolean isFormatoValido(String nome) {
        return nome.endsWith(".csv");
    }

    public static String getDiretorioSaida() {
        return System.getProperty("user.home").concat(BARRA).concat(DIR_DATA)
                                                .concat(BARRA).concat(DIR_OUT).concat(BARRA);
    }

    public static PrintWriter getImprimirSaida() {
        PrintWriter imprimir = null;
        try {
            FileWriter arquivo = new FileWriter(new File(getDiretorioSaida().concat("extrato-vendas.txt")));
            imprimir = new PrintWriter(arquivo);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return imprimir;
    }

    public static void criarDiretorioEntrada() {
        File entrada = new File(getDiretorioEntrata());
        entrada.mkdirs();
    }

    public static void criarDiretorioSaida() {
        File saida = new File(getDiretorioSaida());
        saida.mkdirs();
    }
}
