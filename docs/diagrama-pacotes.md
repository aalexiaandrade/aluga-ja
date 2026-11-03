# Diagrama de Pacotes – Visão Lógica

```mermaid
flowchart TB
  subgraph Frontend["Frontend – React + Vite"]
    Pages["pages"]
    Components["components"]
    Context["context / Auth"]
    Api["services / API client"]
    Pages --> Components
    Pages --> Context
    Pages --> Api
  end
  subgraph Backend["Backend – Spring Boot MVC"]
    Controller["controller"]
    Service["service"]
    Repository["repository"]
    Model["model / entidades"]
    DTO["dto"]
    Security["security"]
    Controller --> Service
    Controller --> DTO
    Service --> Repository
    Service --> Model
    Repository --> Model
    Security --> Controller
  end
  DB[("Supabase PostgreSQL")]
  Api --> Controller
  Repository --> DB
```
