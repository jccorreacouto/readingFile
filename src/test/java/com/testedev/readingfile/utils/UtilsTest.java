package com.testedev.readingfile.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.PrintWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void existeDiretorioEntrada() {
        File file = new File(Utils.getDiretorioEntrata());
        Assert.assertTrue(file.isDirectory());
    }

    @Test
    public void testExisteDiretorioSaida() {
        File file = new File(Utils.getDiretorioSaida());
        Assert.assertTrue(file.isDirectory());
    }

    @Test
    public void testFormatoArquivoInvalido() {
        Assert.assertFalse(Utils.isFormatoValido(".csv"));
    }

    @Test
    public void testValidaArquivoSaida() {
        PrintWriter saida = Utils.getImprimirSaida();
        Assert.assertNotNull(saida);
    }

    @Test
    public void testRemoverArquivosValidosEntrada() {
        String entrada = Utils.getDiretorioEntrata();
        File[] arquivos = Utils.getArquivos(entrada);
        Utils.removerArquivosEntrada(arquivos);
    }

}
