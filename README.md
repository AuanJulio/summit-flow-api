# 🚀 SummitFlow API

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

## 📖 Sobre o Projeto

O **SummitFlow** é uma API RESTful robusta desenvolvida para o gerenciamento de conferências de tecnologia. O sistema permite o controle completo de palestras (**Talks**), palestrantes (**Speakers**) e trilhas de conhecimento (**Tracks**).

O objetivo principal deste projeto foi aplicar as melhores práticas de desenvolvimento com **Spring Boot 3**, focando em:
* Arquitetura limpa e escalável.
* Segurança com **Spring Security** e **JWT**.
* Documentação viva com **OpenAPI (Swagger)**.
* Tratamento de exceções global e padronizado (RFC 7807).
* Performance e integridade de dados (Prevenção de problemas N+1).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security + JWT (JSON Web Token)
* **ORM:** Spring Data JPA / Hibernate
* **Migração de Dados:** Flyway
* **Documentação:** SpringDoc OpenAPI (Swagger UI)
* **Containerização:** Docker & Docker Compose
* **Utils:** Lombok, Bean Validation

---

## ✨ Funcionalidades Principais

* **Autenticação & Segurança:**
    * Registro de usuários e Login retornando Token JWT (Bearer).
    * Proteção de rotas via Filtro de Segurança Customizado.
* **Gestão de Palestras (Talks):**
    * CRUD completo com validações de regras de negócio.
    * Validação em lote de Palestrantes e Trilhas para alta performance.
* **Gestão de Palestrantes (Speakers) e Trilhas (Tracks):**
    * Cadastros independentes para composição da grade do evento.
* **Tratamento de Erros:**
    * Respostas de erro detalhadas (`DetailedError`) para validação de campos.
    * Respostas padronizadas (`StandardError`) para recursos não encontrados.

---

## 🎨 Documentação da API (Swagger)

A API possui documentação interativa completa.
Após rodar o projeto, acesse: http://localhost:8080/swagger-ui.html

> **Destaque:** Todos os DTOs possuem exemplos (`@Schema`) e os erros possíveis (200, 400, 404) estão mapeados.

<img width="1901" height="911" alt="image" src="https://github.com/user-attachments/assets/b94f01cf-aedf-4b60-970f-45c6c416bcfa" />

---

## 📐 Arquitetura e Padrões de Projeto

O projeto segue uma arquitetura em camadas bem definida:

1.  **Controller Layer:** Responsável apenas pela comunicação HTTP e validação básica (`@Valid`).
2.  **Entity Layer:** Representa o modelo de domínio da aplicação. Mapeia as tabelas do banco de dados via JPA/Hibernate, definindo relacionamentos (`@ManyToMany`, `@OneToMany`) e a estrutura dos dados.
3.  **Service Layer:** Contém toda a regra de negócio.
    * *Destaque:* Implementação de validação em lote (`findAllById`) para garantir integridade referencial sem comprometer a performance do banco de dados.
4.  **Repository Layer:** Abstração de acesso a dados com Spring Data JPA.
5.  **Config Layer:** Centraliza as configurações de infraestrutura do projeto, definindo beans do Spring, regras de **Segurança (SecurityFilterChain)**, personalização do **Swagger/OpenAPI** e políticas de **CORS**.
6.  **Mapper Pattern:** Conversão entre Entidades e DTOs (Records) para desacoplar a API do modelo de banco de dados.
7.  **Exception Handler:** Centralização de erros com `@RestControllerAdvice`, garantindo que o cliente da API sempre receba um JSON limpo e explicativo.

---

## 🚀 Como Rodar o Projeto

Este projeto utiliza o **Docker** para gerenciar o Banco de Dados, enquanto a aplicação Java roda localmente para facilitar o desenvolvimento.

### Pré-requisitos
* Java 17 (JDK) e Maven instalados.
* Docker e Docker Compose instalados.
* Uma IDE (IntelliJ ou Eclipse) ou Terminal.

### Passo 1: Clone o repositório

    https://github.com/AuanJulio/summit-flow.git
    cd summit-flow

### Passo 2: Configure o Ambiente

1. Crie um arquivo `.env` na raiz do projeto para configurar as credenciais do banco no Docker:

    DATABASE_DB=summit_db
    DATABASE_USERNAME=postgres
    DATABASE_PASSWORD=postgres

2. **Importante:** Verifique se o seu `src/main/resources/application.properties` está apontando para a porta **5431** (porta exposta pelo Docker):

    spring.datasource.url=jdbc:postgresql://localhost:5431/summit_db
    spring.datasource.username=postgres
    spring.datasource.password=postgres

### Passo 3: Suba o Banco de Dados
Execute o comando abaixo para baixar a imagem e iniciar o PostgreSQL:

    docker-compose up -d

### Passo 4: Execute a Aplicação
Com o banco rodando, inicie a aplicação Spring Boot:

**Via Terminal:**

    # Linux / Mac
    ./mvnw spring-boot:run

    # Windows
    .\mvnw.cmd spring-boot:run

**Ou via IDE:** Abra a classe principal `SummitFlowApplication.java` e clique em "Run".

---

## 🧪 Testando os Endpoints

Para testar, você pode usar o **Swagger UI** ou importar a collection no Postman.

**Fluxo Básico:**
1.  Crie um usuário em `POST /api/v1/auth/register`.
2.  Faça login em `POST /api/v1/auth/login` e copie o **token**.
3.  No Swagger, clique no botão **Authorize** e cole o token.
4.  Agora você pode criar Speakers, Tracks e Talks.

---

## 📝 Autor

Desenvolvido por **Auan Julio**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/auan-julio/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/AuanJulio)
