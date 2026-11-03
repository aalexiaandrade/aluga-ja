package br.edu.pucminas.aluguel.controller;
import br.edu.pucminas.aluguel.dto.AuthDtos.*;
import br.edu.pucminas.aluguel.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service) { this.service=service; }
    @PostMapping("/register") public AuthResponse registrar(@Valid @RequestBody RegistroRequest req) { return service.registrar(req); }
    @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest req) { return service.login(req); }
}
