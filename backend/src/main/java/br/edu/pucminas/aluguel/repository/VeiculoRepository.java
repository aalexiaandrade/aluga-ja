package br.edu.pucminas.aluguel.repository;
import br.edu.pucminas.aluguel.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
