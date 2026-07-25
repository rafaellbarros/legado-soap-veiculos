package br.com.rafaellbarros.legado.soap.veiculos.repository;

import br.com.rafaellbarros.legado.soap.veiculos.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
    
    Optional<VeiculoEntity> findByPlaca(String placa);
    
    boolean existsByPlaca(String placa);
    
    boolean existsByRenavam(String renavam);
}