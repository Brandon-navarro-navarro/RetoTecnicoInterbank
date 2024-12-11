package brandonnavarro.servicio_bff.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import brandonnavarro.servicio_bff.dto.ClienteDTO;
import brandonnavarro.servicio_bff.dto.ProductosDTO;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BffService {
    private static final Logger log = LoggerFactory.getLogger(BffService.class);  // Definir el Logger

    private final WebClient webClient;

    public BffService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<ClienteDTO> getInformacionCliente(String codigoUnico) {
        String trackingId = UUID.randomUUID().toString();

        return webClient.get()
            // .uri("http://localhost:8081/api/cliente/" + codigoUnico)
            .uri("http://microservicio-cliente:8081/api/cliente/" + codigoUnico)
            .retrieve()
            .bodyToMono(ClienteDTO.class)
            .flatMap(client -> webClient.get()
                // .uri("http://localhost:8082/api/productos/" + codigoUnico)
                .uri("http://microservicio-productos:8082/api/productos/" + codigoUnico)
                .retrieve()
                .bodyToFlux(ProductosDTO.class)
                .collectList()
                .doOnNext(products -> client.setProductosFinancieros(products))
                .thenReturn(client)
            )
            .doOnSuccess(client -> log.debug("Tracking ID: {}, Client Info: {}", trackingId, client))
            .doOnError(error -> log.error("Error for Tracking ID: {}", trackingId, error));
    }
}
