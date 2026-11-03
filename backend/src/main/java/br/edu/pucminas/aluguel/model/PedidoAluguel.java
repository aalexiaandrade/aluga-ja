package br.edu.pucminas.aluguel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos_aluguel")
public class PedidoAluguel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne(optional = false) @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    @Column(nullable = false) private LocalDate dataInicio;
    @Column(nullable = false) private LocalDate dataFim;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal valorTotal;
    private Boolean possuiCredito = false;
    @Column(length = 140) private String bancoAgente;
    @Column(precision = 12, scale = 2) private BigDecimal valorCredito;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private StatusPedido status = StatusPedido.PENDENTE;
    @Column(length = 1000) private String parecerAgente;
    @ManyToOne @JoinColumn(name = "agente_id") private Usuario agenteAvaliador;
    @Column(nullable = false) private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @PreUpdate public void atualizarData() { atualizadoEm = LocalDateTime.now(); }
    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Boolean getPossuiCredito() { return possuiCredito; }
    public void setPossuiCredito(Boolean possuiCredito) { this.possuiCredito = possuiCredito; }
    public String getBancoAgente() { return bancoAgente; }
    public void setBancoAgente(String bancoAgente) { this.bancoAgente = bancoAgente; }
    public BigDecimal getValorCredito() { return valorCredito; }
    public void setValorCredito(BigDecimal valorCredito) { this.valorCredito = valorCredito; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public String getParecerAgente() { return parecerAgente; }
    public void setParecerAgente(String parecerAgente) { this.parecerAgente = parecerAgente; }
    public Usuario getAgenteAvaliador() { return agenteAvaliador; }
    public void setAgenteAvaliador(Usuario agenteAvaliador) { this.agenteAvaliador = agenteAvaliador; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}
