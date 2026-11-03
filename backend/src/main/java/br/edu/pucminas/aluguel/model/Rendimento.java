package br.edu.pucminas.aluguel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rendimentos")
public class Rendimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(nullable = false, length = 140)
    private String empregador;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public String getEmpregador() { return empregador; }
    public void setEmpregador(String empregador) { this.empregador = empregador; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
