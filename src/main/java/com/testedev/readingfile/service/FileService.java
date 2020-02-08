package com.testedev.readingfile.service;

import com.testedev.readingfile.domain.dto.*;
import com.testedev.readingfile.mapper.ClienteMapper;
import com.testedev.readingfile.mapper.VendaMapper;
import com.testedev.readingfile.mapper.VendedorMapper;
import com.testedev.readingfile.response.ExtratoResponse;
import com.testedev.readingfile.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
@Component
public class FileService {

    @Autowired
    private VendedorMapper vendedorMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private VendaMapper vendaMapper;

    @Scheduled(cron = "${spring.application.scheduled.cron}")
    public ExtratoResponse processarExtratoVendas() {

        ExtratoResponse response = null;
        File[] arquivos = null;
        long validos = 0L;

        try {
            List<VendedorDTO> vendedores = new ArrayList<VendedorDTO>();
            List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
            List<VendaDTO> vendas = new ArrayList<VendaDTO>();

            String entrada = Utils.getDiretorioEntrata();
            arquivos = Utils.getArquivos(entrada);

            validos = Arrays.stream(arquivos).filter(arquivo -> Utils.isFormatoValido(arquivo.getName())).count();

            for (File arquivo : arquivos) {
                if (Utils.isFormatoValido(arquivo.getName())) {
                    Files.lines(Paths.get(arquivo.toString())).forEach(linha -> {
                        String[] dados = linha.split("ç");
                        switch (Integer.parseInt(dados[0])) {
                            case 1:
                                vendedores.add(this.popularDadosVendedor(dados));
                                break;
                            case 2:
                                clientes.add(this.popularDadosCliente(dados));
                                break;
                            case 3:
                                vendas.add(this.popularDadosVenda(dados));
                                break;
                            default:
                                break;
                        }
                    });
                }
            }

            log.info("Processados na entrada: {}", validos);

            if (vendedores.size() > 0 || clientes.size() > 0 || vendas.size() > 0) {
                this.gerarExtratoVendas(vendedores, clientes, vendas);
                response = ExtratoResponse.builder().qtdeClientes((long) clientes.size())
                                                    .qtdeVendedores((long) vendedores.size())
                                                    .idMaiorVenda(this.getIdMaiorVenda(vendas))
                                                    .piorVendedor(this.getPiorVendedor(vendas))
                                                    .build();
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
            log.error("ERRO AO TENTAR LER DIRETÓRIO DE ENTRADA.");
            Utils.criarDiretorioEntrada();
            Utils.criarDiretorioSaida();
            log.info("OS DIRETÓRIOS DE ENTRADA E SAÍDA DE ARQUIVOS FORAM CRIADOS.");

        } catch (Throwable e) {
            log.error("EXCEÇÃO NÃO TRATADA: " + e.getMessage());
        }

        if(Objects.nonNull(arquivos)) {
            Utils.removerArquivosEntrada(arquivos);
            log.info("Removidos da entrada: {}", validos);
        }

        return Objects.isNull(response) ? ExtratoResponse.builder().build() : response;
    }

    private VendedorDTO popularDadosVendedor(String[] dados) {
        if(dados.length < 4) {
            log.error("Erro ao ler dados/linha do arquivo para Vendedores.");
            return null;
        }
        return vendedorMapper.apply(dados);
    }

    private ClienteDTO popularDadosCliente(String[] dados) {
        if(dados.length < 4) {
            log.error("Erro ao ler dados/linha do arquivo para Clientes.");
            return null;
        }
        return clienteMapper.apply(dados);
    }

    private VendaDTO popularDadosVenda(String[] dados) {
        if(dados.length < 4) {
            log.error("Erro ao ler dados/linha do arquivo para Vendas.");
            return null;
        }
        return vendaMapper.apply(dados);
    }

    private void gerarExtratoVendas(List<VendedorDTO> vendedores, List<ClienteDTO> clientes, List<VendaDTO> vendas) {
        PrintWriter imprimir = Utils.getImprimirSaida();
        imprimir.println("Quantidade de clientes no arquivo de entrada: " + clientes.size());
        imprimir.println("Quantidade de vendedores no arquivo de entrada: " + vendedores.size());
        imprimir.println("ID da venda mais cara: " + this.getIdMaiorVenda(vendas));
        imprimir.println("O pior vendedor: " + this.getPiorVendedor(vendas));
        imprimir.close();
    }

    private Long getIdMaiorVenda(List<VendaDTO> vendas) {
        List<MaiorVendaDTO> maioresVendas = new ArrayList<MaiorVendaDTO>();
        vendas.forEach(venda -> {
            if(Objects.nonNull(venda)) {
                BigDecimal totalVenda = this.getTotalVenda(venda);
                maioresVendas.add(MaiorVendaDTO.builder().idVenda(venda.getIdVenda()).valorTotal(totalVenda).build());
            }
        });
        maioresVendas.sort(Comparator.comparing(MaiorVendaDTO::getValorTotal).reversed());
        return maioresVendas.stream().findFirst().get().getIdVenda();
    }

    private String getPiorVendedor(List<VendaDTO> vendas) {
        List<PiorVendedorDTO> pioresVendedores = new ArrayList<PiorVendedorDTO>();
        vendas.forEach(venda -> {
            if(Objects.nonNull(venda)) {
                BigDecimal totalVenda = this.getTotalVenda(venda);
                pioresVendedores.add(PiorVendedorDTO.builder().nome(venda.getVendedor()).valorTotal(totalVenda).build());
            }
        });
        pioresVendedores.sort(Comparator.comparing(PiorVendedorDTO::getValorTotal));
        return pioresVendedores.stream().findFirst().get().getNome();
    }

    private BigDecimal getTotalVenda(VendaDTO venda) {
        return venda.getItems().stream().map(item -> item.getPreco()).reduce(BigDecimal::add).get();
    }
}
