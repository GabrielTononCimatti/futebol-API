# ⚽ Sistema de Gerenciamento de Pagamentos - API REST (Spring Boot)

## Autores

### **Gabriel Tonon Cimatti | João Vitor Tomadon Daciuk**

Este projeto é uma **API RESTful** desenvolvida com **Java + Spring Boot**, voltada para o **gerenciamento de mensalistas em jogos de futebol**. O sistema segue o padrão arquitetural **MVC** e permite o **cadastro e consulta de jogadores e seus respectivos pagamentos**.

> 💡 **Nota:** O sistema não possui interface gráfica. A interação ocorre por meio de endpoints REST (padrão JSON).

---

## 🧠 Modelo Conceitual

A aplicação é baseada em um modelo relacional simples, com duas entidades principais:

- `jogador`: representa o mensalista (jogador de futebol).
- `pagamento`: representa os pagamentos mensais realizados por cada jogador.

### 🗂️ Relacionamento

- Um **jogador** pode ter **vários pagamentos**.
- Cada **pagamento** está associado a **um único jogador**.

---

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Data JPA
- Maven
- Talend API Tester
- Lombok
- OpenAPI

---

## 🛠️ Endpoints Disponíveis

### 🔹 Jogadores

- `GET /jogadores` – Listar todos os jogadores
- `GET /jogadores/{id}` – Consultar um jogador específico
- `GET /jogadores/{id}/pagamentos` – Consultar pagamentos de um jogador específico
- `POST /jogadores` – Cadastrar um novo jogador
- `PUT /jogadores/{id}` – Atualizar todos os dados de um jogador
- `PATCH /jogadores/{id}` - Atualizar dados de um jogador (qualquer campo)
- `DELETE /jogadores/{id}` – Remover um jogador
- `DELETE /jogadores` – Remove todos os jogadores

### 🔹 Pagamentos

- `GET /pagamentos` – Listar todos os pagamentos (ano e mes/ano)
- `GET /pagamentos/{id}` – Listar pagamentos de um jogador
- `POST /pagamentos` – Registrar um pagamento
- `PUT /pagamentos/{id}` - Atualizar todos os dados de um pagamento pelo id do pagamento
- `PATCH /pagamentos/{id}` - Atualizar dados de um pagamento (qualquer campo)
- `DELETE /pagamentos/{id}` – Excluir um pagamento
- `DELETE /pagamentos` – Remove todos os pagamentos

---

## 📦 Como Executar o Projeto

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Passos

```bash
# Clone o repositório
git clone https://github.com/T0madon/futebol-API.git

# Acesse o diretório
cd futebol-API

# Execute o projeto
./mvnw spring-boot:run
