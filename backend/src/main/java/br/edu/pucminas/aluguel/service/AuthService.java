package br.edu.pucminas.aluguel.service;

import br.edu.pucminas.aluguel.dto.AuthDtos.*;
import br.edu.pucminas.aluguel.model.*;
import br.edu.pucminas.aluguel.repository.*;
import br.edu.pucminas.aluguel.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UsuarioRepository usuarios;
    private final ClienteRepository clientes;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    public AuthService(UsuarioRepository usuarios, ClienteRepository clientes, PasswordEncoder encoder, JwtService jwt) {
        this.usuarios=usuarios; this.clientes=clientes; this.encoder=encoder; this.jwt=jwt;
    }
    @Transactional
    public AuthResponse registrar(RegistroRequest req) {
        if (usuarios.existsByEmail(req.email())) throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        Usuario u = new Usuario(); u.setNome(req.nome()); u.setEmail(req.email().toLowerCase());
        u.setSenha(encoder.encode(req.senha()));
        // O tipo AGENTE é aceito para facilitar a demonstração acadêmica. Restrinja em produção.
        u.setTipo(req.tipo() == null ? TipoUsuario.CLIENTE : req.tipo());
        u = usuarios.save(u);
        if (u.getTipo() == TipoUsuario.CLIENTE) {
            Cliente c = new Cliente(); c.setUsuario(u); c.setRg(req.rg()); c.setCpf(req.cpf());
            c.setEndereco(req.endereco()); c.setProfissao(req.profissao()); clientes.save(c);
        }
        return resposta(u);
    }
    public AuthResponse login(LoginRequest req) {
        Usuario u = usuarios.findByEmail(req.email().toLowerCase())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas."));
        if (!encoder.matches(req.senha(), u.getSenha()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas.");
        return resposta(u);
    }
    private AuthResponse resposta(Usuario u) {
        return new AuthResponse(jwt.gerarToken(u.getEmail(), u.getTipo().name()),
            u.getId(), u.getNome(), u.getEmail(), u.getTipo().name());
    }
}
