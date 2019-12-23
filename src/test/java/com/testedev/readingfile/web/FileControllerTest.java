package com.testedev.readingfile.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileControllerTest extends AbstractFileControllerTest {

    @Autowired
    private FileController ctrl;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void accessUrlVendasExrato() throws Exception {
        String uri = "/vendas/extrato";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void accessMethodVendasExtrato() {
        ctrl.obterExtratoVendas();
    }

}
