package br.edu.pucminas.aluguel.repository;
import br.edu.pucminas.aluguel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsuarioId(Long usuarioId);
}
