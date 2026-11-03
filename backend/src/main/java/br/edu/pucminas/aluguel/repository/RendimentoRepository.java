package br.edu.pucminas.aluguel.repository;
import br.edu.pucminas.aluguel.model.Rendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface RendimentoRepository extends JpaRepository<Rendimento, Long> {
    List<Rendimento> findByClienteId(Long clienteId);
    long countByClienteId(Long clienteId);
}
