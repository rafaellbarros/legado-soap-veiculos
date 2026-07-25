
package br.com.rafaellbarros.legado.soap.veiculos.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "veiculo")
@XmlAccessorType(XmlAccessType.FIELD)
public class VeiculoDTO implements Serializable {
    
    @XmlElement(required = true)
    private String placa;
    
    @XmlElement(required = true)
    private String renavam;
    
    @XmlElement(required = true)
    private String cor;
    
    @XmlElement(required = true)
    private String modelo;


}