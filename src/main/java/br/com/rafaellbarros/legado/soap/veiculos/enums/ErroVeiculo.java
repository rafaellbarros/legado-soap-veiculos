package br.com.rafaellbarros.legado.soap.veiculos.enums;

public enum ErroVeiculo {
    // Validação de campos
    PLACA_OBRIGATORIA("VEIC-001", "Placa é obrigatória"),
    RENAVAM_OBRIGATORIO("VEIC-002", "Renavam é obrigatório"),
    COR_OBRIGATORIA("VEIC-003", "Cor é obrigatória"),
    MODELO_OBRIGATORIO("VEIC-004", "Modelo é obrigatório"),
    
    // Validação de formato
    PLACA_INVALIDA("VEIC-010", "Placa inválida. Formato: ABC-1234 ou ABC1D23"),
    RENAVAM_INVALIDO("VEIC-011", "Renavam inválido. Deve conter 11 dígitos"),
    
    // Duplicidade
    PLACA_JA_EXISTE("VEIC-020", "Placa já cadastrada"),
    RENAVAM_JA_EXISTE("VEIC-021", "Renavam já cadastrado"),
    
    // Não encontrado
    VEICULO_NAO_ENCONTRADO("VEIC-030", "Veículo não encontrado"),
    VEICULO_JA_DESATIVADO("VEIC-031", "Veículo já está desativado"),
    
    // Operações
    ERRO_AO_CADASTRAR("VEIC-040", "Erro ao cadastrar veículo"),
    ERRO_AO_ATUALIZAR("VEIC-041", "Erro ao atualizar veículo"),
    ERRO_AO_DESATIVAR("VEIC-042", "Erro ao desativar veículo");

    private final String codigo;
    private final String mensagem;

    ErroVeiculo(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public String getCodigo() { return codigo; }
    public String getMensagem() { return mensagem; }
}