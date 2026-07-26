package br.com.rafaellbarros.legado.soap.veiculos.config;


import br.com.rafaellbarros.legado.soap.veiculos.ws.VeiculoServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapConfig {


    @Bean
    public EndpointImpl veiculoEndpoint(Bus bus, VeiculoServiceImpl veiculoService) {
        EndpointImpl endpoint = new EndpointImpl(bus, veiculoService);
        endpoint.publish("/veiculo");
        return endpoint;
    }


}