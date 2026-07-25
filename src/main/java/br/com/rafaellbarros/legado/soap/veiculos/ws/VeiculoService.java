package br.com.rafaellbarros.legado.soap.veiculos.ws;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface VeiculoService {

    @WebMethod
    String cadastrarVeiculo(@WebParam(name = "veiculo") VeiculoDTO veiculo);

    @WebMethod
    VeiculoDTO consultarVeiculo(@WebParam(name = "placa") String placa);
}