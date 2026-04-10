# 💰 FinWeb - Sistema de Controle Financeiro

API REST desenvolvida para gerenciamento financeiro pessoal, permitindo que cada usuário tenha controle completo sobre suas receitas e despesas.

O projeto foi construído com foco em boas práticas de desenvolvimento backend, incluindo autenticação, organização em camadas e isolamento de dados por usuário.

---

## 🚀 Funcionalidades

* Cadastro de usuários
* Autenticação com JWT
* CRUD completo de transações
* Filtro de transações por categoria
* Dashboard financeiro com:

  * Total de receitas
  * Total de despesas
  * Saldo final

---

## 🔐 Segurança

* Autenticação baseada em JWT
* Proteção de rotas com Spring Security
* Cada usuário acessa apenas seus próprios dados
* Uso de token no header:

```
Authorization: Bearer SEU_TOKEN
```

---

## ⚙️ Tecnologias utilizadas

* Java
* Spring Boot
* Spring Security
* JWT
* JPA / Hibernate
* Banco de dados relacional

---

## 🧱 Estrutura do projeto

O projeto segue o padrão de arquitetura em camadas:

controller → endpoints da API
service → regras de negócio
repository → acesso ao banco
security → autenticação e filtros JWT
dto → comunicação entre camadas

---

## 📌 Principais endpoints

### 🔑 Autenticação

* POST /login
* POST /login/cadastrar

### 💸 Transações

* GET /transacoes
* POST /transacoes
* PUT /transacoes
* DELETE /transacoes/{id}

### 📊 Dashboard

* GET /transacoes/dashboard

---

## ▶️ Como executar o projeto

Clone o repositório:

```bash
git clone https://github.com/Murilo-dev09/finWeb.git
```

Configure o banco de dados no `application.properties`

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

Acesse o Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 🔄 Próximos passos

* Desenvolvimento do front-end
* Deploy da aplicação
* Melhorias na experiência do usuário

---

## 👨‍💻 Autor

Desenvolvido por Murilo Morales


<img width="1807" height="905" alt="dashboard" src="https://github.com/user-attachments/assets/77e40ec2-6b98-4ef8-90fa-85adca497425" />
<img width="1453" height="664" alt="a" src="https://github.com/user-attachments/assets/b29ded7a-061b-44ce-9f86-a751ea2e300d" />
<img width="1919" height="1033" alt="intel" src="https://github.com/user-attachments/assets/b0f46e67-3625-4be5-aaac-7646e7af039a" />
<img width="1919" height="1035" alt="vs" src="https://github.com/user-attachments/assets/4ec62f2c-7152-48fc-a077-27e5c5f399ab" />



