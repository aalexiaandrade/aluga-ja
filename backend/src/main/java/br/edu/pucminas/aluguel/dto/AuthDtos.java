package br.edu.pucminas.aluguel.dto;

import br.edu.pucminas.aluguel.model.TipoUsuario;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class AuthDtos {
    public record RegistroRequest(
        @NotBlank String nome, @Email @NotBlank String email,
        @Size(min=6) String senha, TipoUsuario tipo,
        String rg, String cpf, String endereco, String profissao
    ) {}
    public record LoginRequest(@Email @NotBlank String email, @NotBlank String senha) {}
    public record AuthResponse(String token, Long id, String nome, String email, String tipo) {}
    public record ClienteResponse(Long id, String nome, String email, String rg, String cpf,
                                  String endereco, String profissao) {}
    public record ClienteUpdateRequest(String nome, String rg, String cpf, String endereco, String profissao) {}
    public record RendimentoRequest(@NotBlank String empregador, @NotNull @DecimalMin("0.01") BigDecimal valor) {}
}
