# Sistema de Aluguel de Carros

Projeto acadêmico do Laboratório 02 – DIAW (PUC Minas).

## Stack
- Java 25, Spring Boot 4.0.x, Maven
- React + Vite
- Supabase PostgreSQL
- Spring Security + JWT

## Estrutura
- `backend/`: API REST em arquitetura MVC (controller, service, repository, model, dto, security).
- `frontend/`: aplicação React.
- `database/schema.sql`: estrutura inicial do banco.
- `docs/`: diagramas UML em Mermaid.

## 1. Configurar o Supabase
1. Crie um projeto no Supabase.
2. Abra **SQL Editor** e execute `database/schema.sql`.
3. Em **Project Settings > Database**, copie a connection string PostgreSQL.
4. Preencha `backend/src/main/resources/application.properties` ou configure as variáveis de ambiente abaixo.

Variáveis:
```env
DB_URL=jdbc:postgresql://db.SEUPROJETO.supabase.co:5432/postgres?sslmode=require
DB_USERNAME=postgres
DB_PASSWORD=SUA_SENHA
JWT_SECRET=uma-chave-secreta-com-pelo-menos-32-caracteres
```

> O backend usa conexão direta PostgreSQL. Não é necessário habilitar autenticação do Supabase: o login da aplicação é próprio e as senhas são armazenadas com BCrypt.

## 2. Executar o backend
Requisitos: JDK 25 e Maven 3.9+.

```bash
cd backend
mvn spring-boot:run
```
API: `http://localhost:8080/api`

## 3. Executar o frontend
Requisitos: Node.js 20+.

```bash
cd frontend
npm install
npm run dev
```
Aplicação: `http://localhost:5173`

## 4. Primeiro acesso
Cadastre um usuário pela tela inicial. Para criar um agente, use o endpoint de cadastro com `tipo: "AGENTE"` ou altere o tipo diretamente no banco. Em uma entrega real, a criação de agentes deve ser restrita a um administrador.

Exemplo de cadastro:
```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "senha": "123456",
  "tipo": "CLIENTE",
  "cpf": "12345678900",
  "rg": "MG123456",
  "endereco": "Rua A, 100",
  "profissao": "Analista"
}
```

## 5. Principais endpoints
| Método | Endpoint | Acesso | Descrição |
|---|---|---|---|
| POST | `/api/auth/register` | Público | Cadastro de cliente ou agente |
| POST | `/api/auth/login` | Público | Login e emissão de JWT |
| GET/PUT | `/api/clientes/me` | CLIENTE | Consultar/alterar perfil |
| GET | `/api/clientes` | AGENTE | Listar clientes |
| POST | `/api/pedidos` | CLIENTE | Criar pedido |
| GET | `/api/pedidos/meus` | CLIENTE | Listar pedidos do cliente |
| GET | `/api/pedidos` | AGENTE | Listar pedidos para análise |
| GET | `/api/pedidos/{id}` | Autenticado | Detalhar pedido |
| PUT | `/api/pedidos/{id}` | CLIENTE | Alterar pedido em análise |
| PATCH | `/api/pedidos/{id}/cancelar` | CLIENTE | Cancelar pedido |
| PATCH | `/api/pedidos/{id}/avaliar` | AGENTE | Aprovar/reprovar pedido |

## Regras implementadas
- Login obrigatório para acessar funcionalidades privadas.
- Clientes criam, consultam, alteram e cancelam seus próprios pedidos.
- Agentes consultam e avaliam pedidos.
- Pedido aprovado fica com status `APROVADO`; reprovado, `REPROVADO`.
- Pedido só pode ser alterado/cancelado enquanto estiver `PENDENTE`.
- Um cliente pode informar até três vínculos empregatícios/rendimentos.
- O automóvel registra matrícula, ano, marca, modelo e placa.
- O pedido pode informar banco agente e dados de crédito.
- O proprietário do veículo pode ser CLIENTE, EMPRESA ou BANCO.
