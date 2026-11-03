package br.edu.pucminas.aluguel.controller;

import br.edu.pucminas.aluguel.model.*;
import br.edu.pucminas.aluguel.repository.VeiculoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController @RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoRepository repository;
    public VeiculoController(VeiculoRepository repository) { this.repository=repository; }
    @GetMapping public List<Veiculo> listar() { return repository.findAll(); }
    @PostMapping @PreAuthorize("hasRole('AGENTE')")
    public Veiculo criar(@Valid @RequestBody Veiculo v) {
        if (repository.findAll().stream().anyMatch(x -> x.getPlaca().equalsIgnoreCase(v.getPlaca())))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Placa já cadastrada.");
        return repository.save(v);
    }
}
