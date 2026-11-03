package br.edu.pucminas.aluguel.repository;
import br.edu.pucminas.aluguel.model.PedidoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
    List<PedidoAluguel> findByClienteIdOrderByCriadoEmDesc(Long clienteId);
    List<PedidoAluguel> findAllByOrderByCriadoEmDesc();
}
