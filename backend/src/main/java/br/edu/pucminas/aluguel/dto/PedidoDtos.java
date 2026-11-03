package br.edu.pucminas.aluguel.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDtos {
    public record PedidoRequest(@NotNull Long veiculoId, @NotNull LocalDate dataInicio,
        @NotNull LocalDate dataFim, @NotNull @DecimalMin("0.01") BigDecimal valorTotal,
        Boolean possuiCredito, String bancoAgente, BigDecimal valorCredito) {}
    public record AvaliacaoRequest(@NotNull Boolean aprovado, @NotBlank String parecer) {}
    public record PedidoResponse(Long id, String cliente, String veiculo, String placa,
        LocalDate dataInicio, LocalDate dataFim, BigDecimal valorTotal, Boolean possuiCredito,
        String bancoAgente, BigDecimal valorCredito, String status, String parecerAgente,
        String agente, String criadoEm) {}
}
