package br.edu.pucminas.aluguel.controller;
import br.edu.pucminas.aluguel.dto.PedidoDtos.*;
import br.edu.pucminas.aluguel.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;
    public PedidoController(PedidoService service) { this.service=service; }
    @PostMapping @PreAuthorize("hasRole('CLIENTE')")
    public PedidoResponse criar(Authentication a,@Valid @RequestBody PedidoRequest r) { return service.criar(a.getName(),r); }
    @GetMapping("/meus") @PreAuthorize("hasRole('CLIENTE')")
    public List<PedidoResponse> meus(Authentication a) { return service.meus(a.getName()); }
    @GetMapping @PreAuthorize("hasRole('AGENTE')")
    public List<PedidoResponse> todos() { return service.todos(); }
    @GetMapping("/{id}") public PedidoResponse buscar(@PathVariable Long id,Authentication a) { return service.buscar(id,a.getName()); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('CLIENTE')")
    public PedidoResponse atualizar(@PathVariable Long id,Authentication a,@Valid @RequestBody PedidoRequest r) { return service.atualizar(id,a.getName(),r); }
    @PatchMapping("/{id}/cancelar") @PreAuthorize("hasRole('CLIENTE')")
    public PedidoResponse cancelar(@PathVariable Long id,Authentication a) { return service.cancelar(id,a.getName()); }
    @PatchMapping("/{id}/avaliar") @PreAuthorize("hasRole('AGENTE')")
    public PedidoResponse avaliar(@PathVariable Long id,Authentication a,@Valid @RequestBody AvaliacaoRequest r) { return service.avaliar(id,a.getName(),r); }
}
