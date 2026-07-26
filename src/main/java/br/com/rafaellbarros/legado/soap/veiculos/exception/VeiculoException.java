package br.com.rafaellbarros.legado.soap.veiculos.exception;

import lombok.Getter;

@Getter
public class VeiculoException extends Exception {

    private final String codigo;
    private final String mensagem;
    private final String detalhes;

    public VeiculoException(String codigo, String mensagem) {
        this(codigo, mensagem, null);
    }

    public VeiculoException(String codigo, String mensagem, String detalhes) {
        super(mensagem);
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

}