# Order API

REST API para gerenciamento de pedidos desenvolvida com **Java e Spring Boot**, com aplicação do padrão de projeto **Strategy** para encapsular as diferentes regras de cálculo de frete.

O projeto foi desenvolvido como parte de um estudo prático de **Design Patterns, APIs REST, Spring Data JPA e persistência com MySQL**.

---

## Sobre o projeto

A **Order API** permite criar e consultar pedidos, além de atualizar seu status e removê-los.

Um dos principais objetivos do projeto é demonstrar como o padrão **Strategy** pode ser utilizado para separar regras de negócio que variam de acordo com o tipo do pedido.

Atualmente, a API possui três modalidades de pedido:

* `NORMAL`
* `EXPRESS`
* `PICKUP`

Cada modalidade possui uma estratégia própria para calcular o custo de frete.

---

## Design Pattern — Strategy

O padrão **Strategy** foi utilizado para separar as diferentes regras de cálculo de frete.

Em vez de utilizar uma estrutura com vários `if/else` ou `switch` dentro do serviço, cada regra é encapsulada em uma implementação própria da interface `ShippingStrategy`.

```text
ShippingStrategy
       │
       ├── NormalShippingStrategy
       ├── ExpressShippingStrategy
       └── PickupShippingStrategy
```

O `OrderService` identifica a estratégia correspondente ao tipo do pedido e delega o cálculo do frete para ela.

### Regras atuais

| Tipo      | Regra                                                               |
| --------- | ------------------------------------------------------------------- |
| `NORMAL`  | Frete grátis para pedidos a partir de R$ 100; caso contrário, R$ 10 |
| `EXPRESS` | R$ 10 para pedidos a partir de R$ 100; caso contrário, R$ 20        |
| `PICKUP`  | Frete grátis                                                        |

Dessa forma, novas estratégias podem ser adicionadas sem alterar a lógica principal de criação dos pedidos.

---

## Arquitetura

O projeto utiliza uma separação simples de responsabilidades entre as principais camadas:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ├── Shipping Strategy
   │
   ▼
Repository
   │
   ▼
Hibernate / JPA
   │
   ▼
MySQL
```

### Responsabilidades

**Controller**

Responsável pela comunicação HTTP e pelos endpoints da API.

**Service**

Responsável pela orquestração das operações e regras relacionadas ao fluxo de criação dos pedidos.

**Strategy**

Responsável pelas regras específicas de cálculo de frete.

**Repository**

Responsável pelo acesso aos dados utilizando Spring Data JPA.

**Model**

Representa as entidades persistidas no banco de dados.

---

## Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* OpenAPI / Swagger
* Design Pattern Strategy

---

## Estrutura do projeto

```text
src
└── main
    ├── java
    │   └── com.henriquefestraits.order_api
    │       ├── controller
    │       │   └── OrderRestController
    │       │
    │       ├── enums
    │       │   ├── OrderStatus
    │       │   └── OrderType
    │       │
    │       ├── model
    │       │   └── Order
    │       │
    │       ├── repository
    │       │   └── OrderRepository
    │       │
    │       ├── service
    │       │   ├── OrderService
    │       │   └── impl
    │       │       └── OrderServiceImpl
    │       │
    │       └── strategy
    │           ├── ShippingStrategy
    │           ├── NormalShippingStrategy
    │           ├── ExpressShippingStrategy
    │           └── PickupShippingStrategy
    │
    └── resources
        └── application.properties
```

---

## Endpoints

### Criar pedido

```http
POST /orders
```

Exemplo de requisição:

```json
{
  "customerName": "Henrique",
  "orderType": "NORMAL",
  "totalAmount": 150
}
```

A API calcula automaticamente o frete e o valor final do pedido.

Exemplo de resposta:

```json
{
  "id": 1,
  "customerName": "Henrique",
  "orderType": "NORMAL",
  "orderStatus": "PENDING",
  "totalAmount": 150,
  "shippingCost": 0,
  "finalValue": 150,
  "createdAt": "2026-08-12T00:00:00"
}
```

---

### Listar pedidos

```http
GET /orders
```

Retorna todos os pedidos cadastrados.

---

### Buscar pedido por ID

```http
GET /orders/{id}
```

Exemplo:

```http
GET /orders/1
```

---

### Atualizar status

```http
PATCH /orders/{id}/status/{status}
```

Exemplo:

```http
PATCH /orders/1/status/SHIPPED
```

Status disponíveis:

```text
PENDING
PROCESSING
SHIPPED
DELIVERED
CANCELED
```

---

### Excluir pedido

```http
DELETE /orders/{id}
```

Exemplo:

```http
DELETE /orders/1
```

---

## Banco de dados

O projeto utiliza **MySQL** para persistência dos pedidos.

Crie o banco de dados:

```sql
CREATE DATABASE order_api;
```

A configuração da aplicação utiliza variáveis de ambiente para evitar que credenciais sejam armazenadas diretamente no código:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/order_api
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Defina a variável de ambiente `DB_PASSWORD` com a senha do seu usuário do MySQL antes de executar a aplicação.

---

## Como executar

### Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java
* Maven
* MySQL

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entre na pasta

```bash
cd order-api
```

### 3. Configure o banco de dados

Crie o database:

```sql
CREATE DATABASE order_api;
```

Configure a variável de ambiente:

```text
DB_PASSWORD=sua_senha
```

### 4. Execute a aplicação

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta:

```text
8080
```

---

## Documentação da API

A API possui documentação através do **OpenAPI/Swagger**.

Com a aplicação em execução, a interface do Swagger pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

Através dela é possível visualizar e testar os endpoints diretamente pelo navegador.

---

## Próximos passos

O projeto está sendo desenvolvido de forma incremental. Algumas melhorias planejadas incluem:

* [ ] Implementação de DTOs para request e response
* [ ] Validação dos dados recebidos
* [ ] Tratamento global de exceções
* [ ] Retorno adequado para recursos não encontrados (`404`)
* [ ] Melhorias na documentação OpenAPI
* [ ] Testes unitários e de integração
* [ ] Melhorias na configuração de ambientes
* [ ] Dockerização da aplicação
* [ ] Melhorias na estrutura de segurança
* [ ] Deploy da aplicação

---

## Objetivo de aprendizado

Este projeto tem como objetivo consolidar conhecimentos em:

* Desenvolvimento de APIs REST
* Spring Boot
* Spring Data JPA
* Hibernate
* Persistência com MySQL
* Injeção de dependências
* Separação de responsabilidades
* Design Patterns
* Strategy Pattern
* Documentação de APIs
* Boas práticas de desenvolvimento Back-End

O projeto também serve como base para futuras evoluções e aplicações mais próximas de um cenário profissional.
