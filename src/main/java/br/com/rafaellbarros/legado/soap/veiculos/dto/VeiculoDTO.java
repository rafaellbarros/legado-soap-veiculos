
package br.com.rafaellbarros.legado.soap.veiculos.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "veiculo")
@XmlAccessorType(XmlAccessType.FIELD)
public class VeiculoDTO implements Serializable {


    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @XmlElement(required = true)
    private String placa;

    @XmlElement(required = true)
    private String renavam;

    @XmlElement(required = true)
    private String cor;

    @XmlElement(required = true)
    private String modelo;

    @XmlElement
    private Boolean ativo;

    @XmlElement
    private String dataCadastro;

    @XmlElement
    private String dataAtualizacao;

    @XmlElement
    private String dataDesativacao;

    public VeiculoDTO(String placa, String renavam, String cor, String modelo) {
        this.placa = placa;
        this.renavam = renavam;
        this.cor = cor;
        this.modelo = modelo;
        this.ativo = true;
    }

    public VeiculoDTO(String placa, String renavam, String cor, String modelo,
                      Boolean ativo, LocalDateTime dataCadastro,
                      LocalDateTime dataAtualizacao, LocalDateTime dataDesativacao) {
        this.placa = placa;
        this.renavam = renavam;
        this.cor = cor;
        this.modelo = modelo;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro != null ? dataCadastro.format(FORMATTER) : null;
        this.dataAtualizacao = dataAtualizacao != null ? dataAtualizacao.format(FORMATTER) : null;
        this.dataDesativacao = dataDesativacao != null ? dataDesativacao.format(FORMATTER) : null;
    }


}