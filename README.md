[![Build Status](https://travis-ci.com/jccorreacouto/readingfile.svg?branch=master)](https://travis-ci.com/jccorreacouto/readingfile)
[![Coverage Status](https://coveralls.io/repos/github/jccorreacouto/readingfile/badge.svg?branch=develop)](https://coveralls.io/github/jccorreacouto/readingfile?branch=develop)

# Reading File
Este projeto permite a leitura de arquivos com extensão ``.dat`` localizados no diretório ``/data/in`` do usuário logado (``HOMEPATH``), 
faz a interpretação dos dados e gera, no diretório ``/data/out`` ao final da execução, um arquivo de texto contendo o resultado das informações
tratadas.
Neste primeiro momento, o arquivo de entrada contém dados de Vendedores, de Clientes, de Vendas e items da Venda.

### Características do desenvolvimento
* Linguagem de Programação Java, versão 8 (1.8.0_231), para desenvolvimento do código fonte.
* Pacote Lombok, para anotações dos atributos e métodos das classes.
* Framework Spring Boot (versão 2.2.2.RELEASE) e suas bibliotecas, para publicação e gerenciamento da aplicação.
* Junit 4, para criação dos testes unitários.

### Subindo o projeto
Para iniciar o container é necessário que o comando ``mvn spring-boot:run`` seja executado na raiz do projeto.

### Cenário
* [descricao-projeto.pdf](/descricao-projeto.pdf)

### Notas importantes
1. Caso os diretórios de entrada e saída não estejam criados, ou tenham sido apagados, a aplicação irá criá-los.
2. A aplicação, após iniciado o container, está configurada para rodar a cada 30 segundos (ou **30000 milisegundos**), 
tal configuração está anotada na classe [FileService](/src/main/java/com/testedev/readingfile/service/FileService.java), 
linha 40, com a anotação do Spring **@Scheduled**.

### Possibilidades de melhorias
1. Criar no arquivo ``application.properties`` as variáveis do sistema para:
    * Diretório de entrada de arquivos
    * Diretório de saída de arquivos
    * Intervalo de execução do scheduling  
2. Alterar o caractere de escape de ``ç`` para ``;``, evitando assim possíveis tratamentos incorretos nas strings.
3. Tratar os métodos privados (``Code Smell``) para ampliar a cobertura dos testes unitários.

### Documentação referência

* [Java 8](https://docs.oracle.com/javase/8/docs/)
* [Lombok](https://projectlombok.org/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/getting-started.html)
* [JUnit 4](https://junit.org/junit4/)

### Documentação complementar
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#using-boot-devtools)

