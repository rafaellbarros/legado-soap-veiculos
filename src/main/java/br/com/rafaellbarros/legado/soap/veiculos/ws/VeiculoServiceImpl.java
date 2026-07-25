package br.com.rafaellbarros.legado.soap.veiculos.ws;


import br.com.rafaellbarros.legado.soap.veiculos.dto.VeiculoDTO;
import br.com.rafaellbarros.legado.soap.veiculos.entity.VeiculoEntity;
import br.com.rafaellbarros.legado.soap.veiculos.repository.VeiculoRepository;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final VeiculoRepository veiculoRepository;

    @Override
    @Transactional
    public String cadastrarVeiculo(VeiculoDTO veiculo) {
        VeiculoEntity entity = VeiculoEntity.fromDTO(veiculo);
        veiculoRepository.save(entity);

        String resultado = "Veículo cadastrado com sucesso: " + veiculo;

        log.info("cadastrarVeiculo() {}", resultado);

        return resultado;
    }

    @Override
    public VeiculoDTO consultarVeiculo(String placa) {
        VeiculoDTO veiculoDTO = veiculoRepository.findByPlaca(placa)
                .map(VeiculoEntity::toDTO)
                .orElse(null);
        log.info("consultarVeiculo() {}", veiculoDTO);
        return veiculoDTO;
    }
}