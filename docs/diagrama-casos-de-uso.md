# Diagrama de Casos de Uso

```mermaid
flowchart LR
    Cliente([Cliente])
    Agente([Agente])
    subgraph Sistema["Sistema de Aluguel de Carros"]
      Login((Autenticar-se))
      Cadastro((Cadastrar-se))
      Criar((Criar pedido))
      Consultar((Consultar pedidos))
      Alterar((Modificar pedido))
      Cancelar((Cancelar pedido))
      Perfil((Gerenciar dados cadastrais))
      Renda((Informar rendimentos))
      Avaliar((Avaliar pedido))
      Clientes((Consultar clientes))
    end
    Cliente --> Cadastro
    Cliente --> Login
    Cliente --> Criar
    Cliente --> Consultar
    Cliente --> Alterar
    Cliente --> Cancelar
    Cliente --> Perfil
    Cliente --> Renda
    Agente --> Login
    Agente --> Avaliar
    Agente --> Consultar
    Agente --> Clientes
```
