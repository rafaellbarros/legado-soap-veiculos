package br.com.rafaellbarros.legado.soap.veiculos.entity;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "veiculos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "placa"),
    @UniqueConstraint(columnNames = "renavam")
})
public class VeiculoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", length = 10, nullable = false, unique = true)
    private String placa;

    @Column(name = "renavam", length = 11, nullable = false, unique = true)
    private String renavam;

    @Column(name = "cor", length = 30, nullable = false)
    private String cor;

    @Column(name = "modelo", length = 50, nullable = false)
    private String modelo;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_desativacao")
    private LocalDateTime dataDesativacao;

    public VeiculoEntity() {
        this.ativo = true;
        this.dataCadastro = LocalDateTime.now();
    }

    public VeiculoEntity(String placa, String renavam, String cor, String modelo) {
        this.placa = placa;
        this.renavam = renavam;
        this.cor = cor;
        this.modelo = modelo;
        this.ativo = true;
        this.dataCadastro = LocalDateTime.now();
    }

    public VeiculoDTO toDTO() {
        return new VeiculoDTO(
                this.placa,
                this.renavam,
                this.cor,
                this.modelo,
                this.ativo,
                this.dataCadastro,
                this.dataAtualizacao,
                this.dataDesativacao
        );
    }

    public static VeiculoEntity fromDTO(VeiculoDTO dto) {
        VeiculoEntity entity = new VeiculoEntity();
        entity.setPlaca(dto.getPlaca());
        entity.setRenavam(dto.getRenavam());
        entity.setCor(dto.getCor());
        entity.setModelo(dto.getModelo());
        entity.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        return entity;
    }
}