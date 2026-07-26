package br.com.rafaellbarros.legado.soap.veiculos.ws;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import br.com.rafaellbarros.legado.soap.veiculos.exception.VeiculoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService(targetNamespace = "http://ws.veiculos.soap.legado.rafaellbarros.com.br/")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface VeiculoService {

    @WebMethod(operationName = "cadastrarVeiculo")
    String cadastrarVeiculo(
            @WebParam(name = "veiculo") VeiculoDTO veiculo
    ) throws VeiculoException;

    @WebMethod(operationName = "consultarVeiculo")
    VeiculoDTO consultarVeiculo(
            @WebParam(name = "placa") String placa
    ) throws VeiculoException;

    @WebMethod(operationName = "atualizarVeiculo")
    String atualizarVeiculo(
            @WebParam(name = "placa") String placa,
            @WebParam(name = "veiculo") VeiculoDTO veiculo
    ) throws VeiculoException;

    @WebMethod(operationName = "desativarVeiculo")
    String desativarVeiculo(
            @WebParam(name = "placa") String placa
    ) throws VeiculoException;

    @WebMethod(operationName = "listarVeiculos")
    List<VeiculoDTO> listarVeiculos(
            @WebParam(name = "apenasAtivos") Boolean apenasAtivos
    ) throws VeiculoException;

    @WebMethod(operationName = "listarPorModelo")
    List<VeiculoDTO> listarPorModelo(
            @WebParam(name = "modelo") String modelo
    ) throws VeiculoException;
}