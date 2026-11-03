package main.java.com.example.alugaja.repository;

import main.java.com.example.alugaja.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsernameAndSenha(String username, String senha);
}