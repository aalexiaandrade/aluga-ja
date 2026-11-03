package main.java.com.example.alugaja.controller;

import main.java.com.example.alugaja.model.Cliente;
import main.java.com.example.alugaja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        if (cliente.getRendimentos() != null && cliente.getRendimentos().size() > 3) {
            throw new IllegalArgumentException("O cliente só pode ter no máximo 3 rendimentos registrados.");
        }
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente dadosAtualizados) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(dadosAtualizados.getNome());
            cliente.setRg(dadosAtualizados.getRg());
            cliente.setCpf(dadosAtualizados.getCpf());
            cliente.setEndereco(dadosAtualizados.getEndereco());
            cliente.setProfissao(dadosAtualizados.getProfissao());
            cliente.setEntidadeEmpregadora(dadosAtualizados.getEntidadeEmpregadora());
            if (dadosAtualizados.getRendimentos() != null && dadosAtualizados.getRendimentos().size() <= 3) {
                cliente.setRendimentos(dadosAtualizados.getRendimentos());
            }
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}