# FinWeb - API de Controle Financeiro

## Sobre o projeto

O **FinWeb** é uma API REST desenvolvida em Java com Spring Boot para gerenciamento financeiro pessoal.

A aplicação permite que cada usuário tenha controle total sobre suas transações financeiras, garantindo segurança e isolamento de dados por meio de autenticação com JWT.

---

##Funcionalidades

* Autenticação de usuários (login com token JWT)
* Cadastro de usuários
* CRUD completo de transações:
  
    * Criar transações
    * Listar transações
    * Atualizar transações
    * Deletar transações
* Filtro de transações por categoria
* Dashboard financeiro com:
  
    * Total de receitas
    * Total de despesas
    * Saldo final

---

## Segurança

* Autenticação baseada em **JWT (JSON Web Token)**
* Cada usuário acessa apenas seus próprios dados
* As requisições são protegidas via token no header:

```
Authorization: Bearer SEU_TOKEN
```

---

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Security
* JWT (Auth0)
* JPA / Hibernate
* Banco de dados relacional

---

## Endpoints principais

### Autenticação

* `POST /login`

### Cadastrar Usuários

* `POST /login/cadastrar`

### Transações

* `GET /transacoes` -> Listar
* `POST /transacoes` -> Cadastrar
* `PUT /transacoes` -> Atualizar
* `DELETE /transacoes/{id}` -> Deletar por id

### Filtro por categoria

* `GET /transacoes/por-categoria` -> Listar por categorias de despesas

### Dashboard

* `GET /transacoes/dashboard` -> DashBoard de todas as receitas/despesas e seu saldo final

---

## Estrutura do projeto

* `controller` → endpoints da API
* `service` → regras de negócio
* `repository` → acesso ao banco
* `security` → autenticação e filtros JWT
* `dto` → comunicação entre camadas

---

## Como executar o projeto

1. Clone o repositório:

```
git clone https://github.com/seu-usuario/finweb.git](https://github.com/Murilo-dev09/finWeb.git

```

2. Configure o banco de dados no `application.properties`

3. Execute a aplicação:

```
./mvnw spring-boot:run
```

4. Acesse o Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## Próximos passos

* Desenvolvimento do front-end
* Melhorias na experiência do usuário
* Deploy da aplicação

---

## Autor

Desenvolvido por Murilo Morales
