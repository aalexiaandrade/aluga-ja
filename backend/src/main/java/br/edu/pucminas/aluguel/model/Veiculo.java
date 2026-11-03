package br.edu.pucminas.aluguel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30) private String matricula;
    @Column(nullable = false) private Integer ano;
    @Column(nullable = false, length = 70) private String marca;
    @Column(nullable = false, length = 70) private String modelo;
    @Column(nullable = false, unique = true, length = 10) private String placa;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private ProprietarioVeiculo proprietario = ProprietarioVeiculo.EMPRESA;

    public Long getId() { return id; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public ProprietarioVeiculo getProprietario() { return proprietario; }
    public void setProprietario(ProprietarioVeiculo proprietario) { this.proprietario = proprietario; }
}
