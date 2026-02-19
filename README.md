
# Address Book Challenge

## 📌 Visão Geral

Este projeto é uma solução para o desafio técnico *Address Book*.

A aplicação lê um arquivo texto contendo informações de pessoas e retorna:

- A quantidade de homens no endereço
- O nome da pessoa mais velha
- A diferença de idade em dias entre Bill McKnight e Paul Robinson

O foco da implementação foi clareza, separação de responsabilidades e testabilidade.

---

## ✅ Pré-requisitos

Para executar o projeto é necessário ter:

- **Java 17** instalado na máquina
- Variável de ambiente `JAVA_HOME` configurada corretamente

### ❗ Maven não é obrigatório

Este projeto utiliza **Maven Wrapper**.

Isso significa que:

- Não é necessário ter o Maven instalado na máquina
- O próprio projeto baixa automaticamente a versão correta do Maven na primeira execução
- Garante consistência de versão entre ambientes diferentes

---

## 📂 Estrutura do Projeto

src  
├── main  
│   ├── java  
│   │   └── com.challenge  
│   │       ├── application  
│   │       ├── domain  
│   │       ├── parser  
│   │       └── service  
│   └── resources  
│       └── AddressBook.txt  
└── test  
└── java

---

## 🏗 Decisões de Design

### Abordagem orientada ao domínio

A entidade `Person` foi implementada como um `record` do Java,
garantindo imutabilidade e deixando o modelo de domínio explícito e conciso.

Validações básicas são feitas na camada de domínio para evitar a criação de objetos inválidos (fail-fast).

---

### Separação de responsabilidades

O projeto foi organizado em camadas bem definidas:

- `application` → Orquestração e ponto de entrada da aplicação
- `domain` → Modelo de domínio
- `parser` → Responsável por interpretar o arquivo de entrada
- `service` → Regras de negócio

Essa organização mantém parsing, regras de negócio e fluxo de execução desacoplados, facilitando manutenção e testes.

---

### Isolamento da regra de negócio

Toda a lógica de negócio (contagem de homens, identificação do mais velho, cálculo de diferença de idade) está concentrada na classe `AddressBookService`.

Isso permite reutilização da lógica independentemente da forma de entrada dos dados.

---

### Tratamento de entrada

A aplicação pode ser executada de duas formas:

1. Informando o caminho do arquivo como argumento
2. Utilizando automaticamente o arquivo `AddressBook.txt` localizado em `src/main/resources`

Caso o arquivo padrão não seja encontrado, a aplicação lança uma exceção clara e interrompe a execução.

---

## ▶ Como Executar

> ⚠️ **Importante (Windows / PowerShell)**  
> No PowerShell é necessário usar `.\` antes do `mvnw.cmd`, pois o terminal não executa comandos do diretório atual por padrão.

### 1️⃣ Gerar o build do projeto

Linux/macOS:

    ./mvnw clean package

Windows (PowerShell):

    .\mvnw.cmd clean package

Na primeira execução, o Maven Wrapper fará o download automático da versão correta do Maven.

---

### 2️⃣ Executar a aplicação via Maven Wrapper

Linux/macOS:

    ./mvnw exec:java -Dexec.mainClass="com.challenge.Main"

Windows (PowerShell):

    .\mvnw.cmd exec:java "-Dexec.mainClass=com.challenge.Main"

---

### 3️⃣ Executar o JAR gerado

Após o build:

    java -jar target/address-book-challenge-1.0.0.jar

Ou informando um arquivo personalizado:

    java -jar target/address-book-challenge-1.0.0.jar caminho/para/arquivo.txt

---

## 🧪 Executando os Testes

Os testes unitários foram implementados utilizando JUnit 5.

Linux/macOS:

    ./mvnw test

Windows (PowerShell):

    .\mvnw.cmd test

Executando apenas uma classe específica:

    .\mvnw.cmd -Dtest=AddressBookServiceTest test

---

## ⚙ Tecnologias Utilizadas

- Java 17
- Maven (via Maven Wrapper)
- JUnit 5
- Uso de `Optional` para evitar tratamento manual de `null`
- `DateTimeFormatterBuilder` para parsing adequado de datas com ano em dois dígitos

---

## 📌 Possíveis Melhorias

Caso o projeto fosse evoluído além do escopo do desafio, algumas melhorias possíveis seriam:

- Suporte a outros formatos de entrada (CSV, JSON)
- Implementação de logging estruturado em vez de `System.out`
- Tratamento mais detalhado de linhas mal formatadas
- Processamento em streaming para arquivos muito grandes
- Inclusão de testes de integração

---

## 🎯 Considerações Finais

O objetivo desta implementação foi manter a solução simples, legível e bem estruturada, evitando complexidade desnecessária.

O foco principal foi:

- Correção
- Clareza
- Organização
- Manutenibilidade
