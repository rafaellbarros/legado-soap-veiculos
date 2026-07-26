package br.com.rafaellbarros.legado.soap.veiculos.ws;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import br.com.rafaellbarros.legado.soap.veiculos.entity.VeiculoEntity;
import br.com.rafaellbarros.legado.soap.veiculos.enums.ErroVeiculo;
import br.com.rafaellbarros.legado.soap.veiculos.exception.VeiculoException;
import br.com.rafaellbarros.legado.soap.veiculos.repository.VeiculoRepository;
import br.com.rafaellbarros.legado.soap.veiculos.validator.VeiculoValidator;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@WebService(
        endpointInterface = "br.com.rafaellbarros.legado.soap.veiculos.ws.VeiculoService",
        serviceName = "VeiculoService",
        portName = "VeiculoPort",
        targetNamespace = "http://ws.veiculos.soap.legado.rafaellbarros.com.br/"
)
public class VeiculoServiceImpl implements VeiculoService {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoServiceImpl.class);

    private final VeiculoRepository veiculoRepository;

    private final VeiculoValidator validator;

    // =============================================
    // CADASTRAR VEÍCULO
    // =============================================
    @Override
    @Transactional
    public String cadastrarVeiculo(VeiculoDTO veiculo) throws VeiculoException {
        logger.info("📝 Cadastrando veículo: {}", veiculo);

        try {
            // Validações
            validator.validarCadastro(veiculo); //

            // Verifica duplicidade (apenas ativos)
            if (veiculoRepository.existsByPlacaAndAtivoTrue(veiculo.getPlaca())) {
                throw new VeiculoException(
                        ErroVeiculo.PLACA_JA_EXISTE.getCodigo(),
                        ErroVeiculo.PLACA_JA_EXISTE.getMensagem(),
                        "Placa: " + veiculo.getPlaca()
                );
            }

            if (veiculoRepository.existsByRenavamAndAtivoTrue(veiculo.getRenavam())) {
                throw new VeiculoException(
                        ErroVeiculo.RENAVAM_JA_EXISTE.getCodigo(),
                        ErroVeiculo.RENAVAM_JA_EXISTE.getMensagem(),
                        "Renavam: " + veiculo.getRenavam()
                );
            }

            // Salva
            VeiculoEntity entity = VeiculoEntity.fromDTO(veiculo);
            veiculoRepository.save(entity);

            logger.info("✅ Veículo cadastrado com sucesso: {}", veiculo);
            return "Veículo cadastrado com sucesso: " + veiculo;

        } catch (VeiculoException e) {
            logger.error("❌ Erro ao cadastrar veículo: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("❌ Erro inesperado ao cadastrar veículo", e);
            throw new VeiculoException(
                    ErroVeiculo.ERRO_AO_CADASTRAR.getCodigo(),
                    ErroVeiculo.ERRO_AO_CADASTRAR.getMensagem(),
                    e.getMessage()
            );
        }
    }

    // =============================================
    // CONSULTAR VEÍCULO
    // =============================================
    @Override
    public VeiculoDTO consultarVeiculo(String placa) throws VeiculoException {
        logger.info("🔍 Consultando veículo com placa: {}", placa);

        if (placa == null || placa.trim().isEmpty()) {
            throw new VeiculoException(
                    ErroVeiculo.PLACA_OBRIGATORIA.getCodigo(),
                    ErroVeiculo.PLACA_OBRIGATORIA.getMensagem()
            );
        }

        return veiculoRepository.findByPlacaAndAtivoTrue(placa.trim().toUpperCase())
                .map(veiculo -> {
                    logger.info("✅ Veículo encontrado: {}", veiculo);
                    return veiculo.toDTO();
                })
                .orElseThrow(() -> {
                    logger.warn("⚠️ Veículo não encontrado: {}", placa);
                    return new VeiculoException(
                            ErroVeiculo.VEICULO_NAO_ENCONTRADO.getCodigo(),
                            ErroVeiculo.VEICULO_NAO_ENCONTRADO.getMensagem(),
                            "Placa: " + placa
                    );
                });
    }

    // =============================================
    // ATUALIZAR VEÍCULO
    // =============================================
    @Override
    @Transactional
    public String atualizarVeiculo(String placa, VeiculoDTO veiculo) throws VeiculoException {
        logger.info("✏️ Atualizando veículo - Placa: {}, Dados: {}", placa, veiculo);

        try {
            // Validações
            if (placa == null || placa.trim().isEmpty()) {
                throw new VeiculoException(
                        ErroVeiculo.PLACA_OBRIGATORIA.getCodigo(),
                        ErroVeiculo.PLACA_OBRIGATORIA.getMensagem()
                );
            }

            validator.validarCadastro(veiculo);

            // Busca o veículo
            VeiculoEntity entity = veiculoRepository.findByPlacaAndAtivoTrue(placa.trim().toUpperCase())
                    .orElseThrow(() -> new VeiculoException(
                            ErroVeiculo.VEICULO_NAO_ENCONTRADO.getCodigo(),
                            ErroVeiculo.VEICULO_NAO_ENCONTRADO.getMensagem(),
                            "Placa: " + placa
                    ));

            // Verifica duplicidade (se a placa for alterada)
            if (!placa.equalsIgnoreCase(veiculo.getPlaca())) {
                if (veiculoRepository.existsByPlacaAndAtivoTrue(veiculo.getPlaca())) {
                    throw new VeiculoException(
                            ErroVeiculo.PLACA_JA_EXISTE.getCodigo(),
                            ErroVeiculo.PLACA_JA_EXISTE.getMensagem(),
                            "Nova placa: " + veiculo.getPlaca()
                    );
                }
            }

            // Atualiza dados
            entity.setPlaca(veiculo.getPlaca().trim().toUpperCase());
            entity.setRenavam(veiculo.getRenavam().trim());
            entity.setCor(veiculo.getCor().trim());
            entity.setModelo(veiculo.getModelo().trim());
            entity.setDataAtualizacao(LocalDateTime.now());

            veiculoRepository.save(entity);

            logger.info("✅ Veículo atualizado com sucesso: {}", veiculo);
            return "Veículo atualizado com sucesso: " + veiculo;

        } catch (VeiculoException e) {
            logger.error("❌ Erro ao atualizar veículo: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("❌ Erro inesperado ao atualizar veículo", e);
            throw new VeiculoException(
                    ErroVeiculo.ERRO_AO_ATUALIZAR.getCodigo(),
                    ErroVeiculo.ERRO_AO_ATUALIZAR.getMensagem(),
                    e.getMessage()
            );
        }
    }

    // =============================================
    // DESATIVAR VEÍCULO (EXCLUSÃO LÓGICA)
    // =============================================
    @Override
    @Transactional
    public String desativarVeiculo(String placa) throws VeiculoException {
        logger.info("🗑️ Desativando veículo com placa: {}", placa);

        if (placa == null || placa.trim().isEmpty()) {
            throw new VeiculoException(
                    ErroVeiculo.PLACA_OBRIGATORIA.getCodigo(),
                    ErroVeiculo.PLACA_OBRIGATORIA.getMensagem()
            );
        }

        // Verifica se existe e está ativo
        VeiculoEntity entity = veiculoRepository.findByPlacaAndAtivoTrue(placa.trim().toUpperCase())
                .orElseThrow(() -> new VeiculoException(
                        ErroVeiculo.VEICULO_NAO_ENCONTRADO.getCodigo(),
                        ErroVeiculo.VEICULO_NAO_ENCONTRADO.getMensagem(),
                        "Placa: " + placa
                ));

        // Desativa
        entity.setAtivo(false);
        entity.setDataDesativacao(LocalDateTime.now());
        veiculoRepository.save(entity);

        logger.info("✅ Veículo desativado com sucesso: {}", placa);
        return "Veículo desativado com sucesso: " + placa;
    }

    // =============================================
    // LISTAR VEÍCULOS
    // =============================================
    @Override
    public List<VeiculoDTO> listarVeiculos(Boolean apenasAtivos) throws VeiculoException {
        logger.info("📋 Listando veículos - Apenas ativos: {}", apenasAtivos);

        try {
            List<VeiculoEntity> veiculos;

            if (apenasAtivos == null || apenasAtivos) {
                veiculos = veiculoRepository.findAllByAtivoTrue();
            } else {
                veiculos = veiculoRepository.findAll();
            }

            List<VeiculoDTO> result = veiculos.stream()
                    .map(VeiculoEntity::toDTO)
                    .collect(Collectors.toList());

            logger.info("✅ Encontrados {} veículos", result.size());
            return result;

        } catch (Exception e) {
            logger.error("❌ Erro ao listar veículos", e);
            throw new VeiculoException(
                    "VEIC-050",
                    "Erro ao listar veículos",
                    e.getMessage()
            );
        }
    }

    // =============================================
    // LISTAR POR MODELO
    // =============================================
    @Override
    public List<VeiculoDTO> listarPorModelo(String modelo) throws VeiculoException {
        logger.info("🔍 Buscando veículos por modelo: {}", modelo);

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new VeiculoException(
                    ErroVeiculo.MODELO_OBRIGATORIO.getCodigo(),
                    ErroVeiculo.MODELO_OBRIGATORIO.getMensagem()
            );
        }

        try {
            List<VeiculoEntity> veiculos = veiculoRepository
                    .findByModeloContainingIgnoreCaseAndAtivoTrue(modelo.trim());

            List<VeiculoDTO> result = veiculos.stream()
                    .map(VeiculoEntity::toDTO)
                    .collect(Collectors.toList());

            logger.info("✅ Encontrados {} veículos para o modelo '{}'", result.size(), modelo);
            return result;

        } catch (Exception e) {
            logger.error("❌ Erro ao buscar veículos por modelo", e);
            throw new VeiculoException(
                    "VEIC-051",
                    "Erro ao buscar veículos por modelo",
                    e.getMessage()
            );
        }
    }
}