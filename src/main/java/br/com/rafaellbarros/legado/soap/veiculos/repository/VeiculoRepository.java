package br.com.rafaellbarros.legado.soap.veiculos.repository;

import br.com.rafaellbarros.legado.soap.veiculos.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    // Buscar por placa (apenas ativos)
    Optional<VeiculoEntity> findByPlacaAndAtivoTrue(String placa);

    // Buscar por placa (todos)
    Optional<VeiculoEntity> findByPlaca(String placa);

    // Buscar apenas ativos
    List<VeiculoEntity> findAllByAtivoTrue();

    // Verificar existência por placa (apenas ativos)
    boolean existsByPlacaAndAtivoTrue(String placa);

    // Verificar existência por renavam (apenas ativos)
    boolean existsByRenavamAndAtivoTrue(String renavam);

    // Verificar existência por placa (todos)
    boolean existsByPlaca(String placa);

    // Verificar existência por renavam (todos)
    boolean existsByRenavam(String renavam);

    // Desativar veículo (exclusão lógica)
    @Modifying
    @Query("UPDATE VeiculoEntity v SET v.ativo = false, v.dataDesativacao = :dataDesativacao WHERE v.placa = :placa")
    int desativarPorPlaca(@Param("placa") String placa, @Param("dataDesativacao") LocalDateTime dataDesativacao);

    // Buscar veículos ativos por modelo (contendo)
    List<VeiculoEntity> findByModeloContainingIgnoreCaseAndAtivoTrue(String modelo);

    // Buscar veículos ativos por cor
    List<VeiculoEntity> findByCorIgnoreCaseAndAtivoTrue(String cor);
}