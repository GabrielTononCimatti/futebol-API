# ⚽ Sistema de Gerenciamento de Pagamentos - API REST (Spring Boot)

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
- H2 Database (para testes)
- Maven
- Lombok
- Swagger/OpenAPI (para documentação, se aplicável)

---

## 🛠️ Endpoints Disponíveis

### 🔹 Jogadores

- `POST /jogadores` – Cadastrar um novo jogador
- `GET /jogadores` – Listar todos os jogadores
- `GET /jogadores/{id}` – Consultar um jogador específico
- `PUT /jogadores/{id}` – Atualizar dados de um jogador
- `DELETE /jogadores/{id}` – Remover um jogador

### 🔹 Pagamentos

- `POST /pagamentos` – Registrar um pagamento
- `GET /pagamentos` – Listar todos os pagamentos
- `GET /pagamentos/jogador/{id}` – Listar pagamentos de um jogador
- `DELETE /pagamentos/{id}` – Excluir um pagamento

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
