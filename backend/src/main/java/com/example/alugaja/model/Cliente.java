package br.pucminas.alugaja.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String username;
    private String senha;
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;
    private String entidadeEmpregadora;

    @ElementCollection
    @CollectionTable(name = "cliente_rendimentos", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "rendimento")
    private List<Double> rendimentos; // Máximo de 3 rendimentos

    public Cliente() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    public String getEntidadeEmpregadora() { return entidadeEmpregadora; }
    public void setEntidadeEmpregadora(String entidadeEmpregadora) { this.entidadeEmpregadora = entidadeEmpregadora; }

    public List<Double> getRendimentos() { return rendimentos; }
    public void setRendimentos(List<Double> rendimentos) { this.rendimentos = rendimentos; }
}