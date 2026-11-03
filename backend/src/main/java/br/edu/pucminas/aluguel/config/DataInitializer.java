package br.edu.pucminas.aluguel.config;

import br.edu.pucminas.aluguel.model.*;
import br.edu.pucminas.aluguel.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seedVeiculos(VeiculoRepository repo) {
        return args -> {
            if (repo.count()==0) {
                Veiculo a=new Veiculo(); a.setMatricula("MAT-001"); a.setAno(2024);
                a.setMarca("Toyota"); a.setModelo("Corolla"); a.setPlaca("ABC1D23");
                a.setProprietario(ProprietarioVeiculo.EMPRESA);
                Veiculo b=new Veiculo(); b.setMatricula("MAT-002"); b.setAno(2023);
                b.setMarca("Chevrolet"); b.setModelo("Onix"); b.setPlaca("DEF4G56");
                b.setProprietario(ProprietarioVeiculo.EMPRESA);
                Veiculo c=new Veiculo(); c.setMatricula("MAT-003"); c.setAno(2025);
                c.setMarca("Hyundai"); c.setModelo("HB20"); c.setPlaca("GHI7J89");
                c.setProprietario(ProprietarioVeiculo.BANCO);
                repo.saveAll(List.of(a,b,c));
            }
        };
    }
}
