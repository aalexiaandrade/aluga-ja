package br.edu.pucminas.aluguel.controller;
import br.edu.pucminas.aluguel.dto.AuthDtos.*;
import br.edu.pucminas.aluguel.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service) { this.service=service; }
    @GetMapping("/me") @PreAuthorize("hasRole('CLIENTE')")
    public ClienteResponse meuPerfil(Authentication a) { return service.meuPerfil(a.getName()); }
    @PutMapping("/me") @PreAuthorize("hasRole('CLIENTE')")
    public ClienteResponse atualizar(Authentication a,@RequestBody ClienteUpdateRequest r) { return service.atualizar(a.getName(),r); }
    @GetMapping @PreAuthorize("hasRole('AGENTE')")
    public List<ClienteResponse> listar() { return service.listar(); }
    @GetMapping("/me/rendimentos") @PreAuthorize("hasRole('CLIENTE')")
    public List<RendimentoRequest> rendimentos(Authentication a) { return service.listarRendimentos(a.getName()); }
    @PostMapping("/me/rendimentos") @PreAuthorize("hasRole('CLIENTE')")
    public List<RendimentoRequest> adicionar(Authentication a,@Valid @RequestBody RendimentoRequest r) { return service.adicionarRendimento(a.getName(),r); }
}
