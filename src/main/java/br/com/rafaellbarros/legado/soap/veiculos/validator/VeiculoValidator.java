package br.com.rafaellbarros.legado.soap.veiculos.validator;

import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import br.com.rafaellbarros.legado.soap.veiculos.enums.ErroVeiculo;
import br.com.rafaellbarros.legado.soap.veiculos.exception.VeiculoException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class VeiculoValidator {

    // Regex para placa no formato antigo: ABC-1234
    private static final Pattern PLACA_ANTIGA = Pattern.compile("^[A-Z]{3}-\\d{4}$");
    
    // Regex para placa Mercosul: ABC1D23
    private static final Pattern PLACA_MERCOSUL = Pattern.compile("^[A-Z]{3}\\d[A-Z]\\d{2}$");

    public void validarCadastro(VeiculoDTO veiculo) throws VeiculoException {
        validarCamposObrigatorios(veiculo);
        validarPlaca(veiculo.getPlaca());
        validarRenavam(veiculo.getRenavam());
    }

    public void validarCamposObrigatorios(VeiculoDTO veiculo) throws VeiculoException {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new VeiculoException(
                ErroVeiculo.PLACA_OBRIGATORIA.getCodigo(),
                ErroVeiculo.PLACA_OBRIGATORIA.getMensagem()
            );
        }

        if (veiculo.getRenavam() == null || veiculo.getRenavam().trim().isEmpty()) {
            throw new VeiculoException(
                ErroVeiculo.RENAVAM_OBRIGATORIO.getCodigo(),
                ErroVeiculo.RENAVAM_OBRIGATORIO.getMensagem()
            );
        }

        if (veiculo.getCor() == null || veiculo.getCor().trim().isEmpty()) {
            throw new VeiculoException(
                ErroVeiculo.COR_OBRIGATORIA.getCodigo(),
                ErroVeiculo.COR_OBRIGATORIA.getMensagem()
            );
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new VeiculoException(
                ErroVeiculo.MODELO_OBRIGATORIO.getCodigo(),
                ErroVeiculo.MODELO_OBRIGATORIO.getMensagem()
            );
        }
    }

    public void validarPlaca(String placa) throws VeiculoException {
        String placaTrim = placa.trim().toUpperCase();
        
        if (!PLACA_ANTIGA.matcher(placaTrim).matches() && 
            !PLACA_MERCOSUL.matcher(placaTrim).matches()) {
            throw new VeiculoException(
                ErroVeiculo.PLACA_INVALIDA.getCodigo(),
                ErroVeiculo.PLACA_INVALIDA.getMensagem(),
                "Formato aceito: ABC-1234 ou ABC1D23. Valor recebido: " + placa
            );
        }
    }

    public void validarRenavam(String renavam) throws VeiculoException {
        String renavamTrim = renavam.trim();
        
        if (!renavamTrim.matches("^\\d{11}$")) {
            throw new VeiculoException(
                ErroVeiculo.RENAVAM_INVALIDO.getCodigo(),
                ErroVeiculo.RENAVAM_INVALIDO.getMensagem(),
                "O Renavam deve conter exatamente 11 dígitos. Valor recebido: " + renavam
            );
        }
    }
}