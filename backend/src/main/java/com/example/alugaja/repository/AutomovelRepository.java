package main.java.com.example.alugaja.repository;

import com.example.alugaja.model.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}