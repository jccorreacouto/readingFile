# Reading File
Este projeto permite a leitura de arquivos com extensão .csv localizados no diretório /data/in do usuário logado (HOMEPATH), 
faz a interpretação dos dados e gera, ao final da execução, um arquivo de texto contendo as informações do extrato de vendas,
tendo por parâmetros de saída as informações de Vendedor, Cliente e Vendas.

### Características
##### No projeto foi utilizado:

* Linguagem de Programação Java, versão 8 (1.8.0_231), para desenvolvimento do código fonte.
* Pacote Lombok, para anotações dos atributos e métodos das classes.
* Framework Spring Boot (versão 2.2.2.RELEASE) e suas bibliotecas, para publicação e gerenciamento da aplicação.
* Junit 4, para criação dos testes unitários.

### Notas importantes
1. A aplicação, após iniciado o container, está configurada para rodar a cada 5 minutos (ou **300000 milisegundos**), 
tal configuração está anotada na classe [FileService](/src/main/java/com/testedev/readingfile/service/FileService.java), 
linha 43, com a anotação do Spring **@Scheduled**.
2. Após iniciado o container, também é possível executar o processo de leitura dos arquivos fazendo uma chamada *GET* 
para a [localhost](http://localhost:8080/vendas/extrato) onde retorna um *JSON* com os seguintes dados:
```
{
    "qtdeClientes": Long,
    "qtdeVendedores":Long,
    "maiorVenda": Long,
    "piorVendedor": String
}
```

### Possibilidade de melhorias
1. Criar o arquivo ``application.properties`` e definir nele as variáveis de ambiente:
    * Diretorio de entrada de arquivos
    * Diretório de saída de arquivos
    * Intervalo de execução do scheduling  

### Documentação referência

* [Java 8](https://docs.oracle.com/javase/8/docs/)
* [Lombok](https://projectlombok.org/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/getting-started.html)
* [JUnit 4](https://junit.org/junit4/)

### Documentações complementares
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#using-boot-devtools)

