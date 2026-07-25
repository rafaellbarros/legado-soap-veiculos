// src/main/java/br/com/rafaellbarros/legado/soap/veiculos/entity/VeiculoEntity.java
package br.com.rafaellbarros.legado.soap.veiculos.entity;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public VeiculoEntity(String placa, String renavam, String cor, String modelo) {
        this.placa = placa;
        this.renavam = renavam;
        this.cor = cor;
        this.modelo = modelo;
    }

    public VeiculoDTO toDTO() {
        return new VeiculoDTO(this.placa, this.renavam, this.cor, this.modelo);
    }


    public static VeiculoEntity fromDTO(VeiculoDTO dto) {
        return new VeiculoEntity(dto.getPlaca(), dto.getRenavam(), dto.getCor(), dto.getModelo());
    }
}