package brandonnavarro.servicio_bff.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import brandonnavarro.servicio_bff.dto.ClienteDTO;
import brandonnavarro.servicio_bff.dto.ProductosDTO;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class BffService {
   
    private final WebClient webClient;
    private String trackingIdAop = "";

    public String getTrackingId() {
        return trackingIdAop;
    }

    public BffService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${microservices.clients-serviceDev}") String UrlBaseClienteDev;
    @Value("${microservices.products-serviceDev}") String UrlBaseProductosDev;
    @Value("${microservices.clients-serviceProd}") String UrlBaseClienteProd;
    @Value("${microservices.products-serviceProd}") String UrlBaseProductosProd;

    public Mono<ClienteDTO> getInformacionCliente(String codigoUnico) {
        String trackingId = UUID.randomUUID().toString();
        this.trackingIdAop = trackingId;

        return webClient.get()
            // .uri( UrlBaseClienteDev + "/api/cliente/" + codigoUnico)
            .uri(UrlBaseClienteProd + "/api/cliente/" + codigoUnico)
            .header("Tracking-ID", trackingId)
            .retrieve()
            .bodyToMono(ClienteDTO.class)
            .flatMap(client -> webClient.get()
                // .uri( UrlBaseProductosDev + "/api/productos/" + codigoUnico)
                .uri(UrlBaseProductosProd + "/api/productos/" + codigoUnico)
                .header("Tracking-ID", trackingId)
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
