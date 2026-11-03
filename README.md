# AlugaJá – Sistema de Aluguel de Carros

Projeto acadêmico desenvolvido para o Laboratório 02 da disciplina **Desenvolvimento e Integração de Aplicações Web (DIAW)**, da PUC Minas.

O **AlugaJá** é uma aplicação web para gerenciamento de aluguel de veículos, permitindo que usuários consultem veículos disponíveis, realizem cadastros e efetuem operações relacionadas à locação.

## Tecnologias utilizadas

### Backend
- Java 21
- Spring Boot 4.0.0
- Maven
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate

### Frontend
- React
- Vite
- JavaScript
- Axios
- React Router DOM

### Banco de dados
- PostgreSQL
- Supabase

## Estrutura do projeto

```text
aluga-ja/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── br/edu/pucminas/aluguel/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       ├── model/
│   │   │   │       └── security/
│   │   │   └── resources/
│   │   │       └── application.properties
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── vite.config.js
│
└── README.md
```

## 2. Executando o backend

### Pré-requisitos
- JDK 21
- Maven 3.9 ou superior

Acesse a pasta do backend:

```bash
cd backend
```

Execute a aplicação:

```bash
mvn clean spring-boot:run
```

Após a inicialização, a API estará disponível em:

```text
http://localhost:8080
```

A conexão com o banco de dados é estabelecida durante a inicialização da aplicação.

## 3. Executando o frontend

### Pré-requisitos
- Node.js
- npm

Acesse a pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Inicie o servidor de desenvolvimento:

```bash
npm run dev
```

O Vite disponibilizará a aplicação em:

```text
http://localhost:5173
```

## 4. Autenticação

O AlugaJá possui sistema próprio de autenticação, utilizando Spring Security e JWT.

As rotas de autenticação são públicas para permitir o cadastro e o login dos usuários.

### Principais operações de autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/auth/register` | Cadastro de usuário |
| POST | `/api/auth/login` | Autenticação e emissão de token JWT |

Após o login, o token JWT é utilizado para autenticar as requisições que exigem acesso protegido.

As senhas são armazenadas utilizando o algoritmo BCrypt.

## 5. Comunicação entre frontend e backend

O frontend realiza requisições HTTP para a API utilizando Axios.

O backend disponibiliza os serviços na porta 8080, enquanto o frontend é executado pelo Vite.

A configuração de CORS permite a comunicação entre as duas aplicações durante o desenvolvimento local.

Origem padrão autorizada:

```text
http://localhost:5173
```

## 6. Funcionalidades do sistema

O AlugaJá foi desenvolvido com o objetivo de oferecer funcionalidades para gerenciamento de aluguel de veículos, incluindo:

- Cadastro de usuários.
- Login e autenticação.
- Consulta de veículos.
- Gerenciamento de veículos.
- Integração com banco de dados PostgreSQL.
- Proteção de rotas por autenticação.
- Gerenciamento de informações relacionadas às locações.

## 7. Execução completa do projeto

Para executar o sistema localmente, é necessário manter dois terminais abertos.

**Terminal 1 – Backend:**

```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 – Frontend:**

```bash
cd frontend
npm install
npm run dev
```

Acesse a aplicação pelo navegador:

```text
http://localhost:5173
```

---

**Desenvolvimento acadêmico – PUC Minas**

Projeto desenvolvido para a disciplina de Desenvolvimento e Integração de Aplicações Web (DIAW).