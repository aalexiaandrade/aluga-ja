package main.java.com.example.alugaja.controller;

import com.example.alugaja.model.Automovel;
import com.example.alugaja.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automoveis")
@CrossOrigin(origins = "*")
public class AutomovelController {

    @Autowired
    private AutomovelRepository automovelRepository;

    @GetMapping
    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    @PostMapping
    public Automovel cadastrar(@RequestBody Automovel automovel) {
        return automovelRepository.save(automovel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> buscarPorId(@PathVariable Long id) {
        return automovelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (automovelRepository.existsById(id)) {
            automovelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}