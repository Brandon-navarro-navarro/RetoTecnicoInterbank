package brandonnavarro.microservicio_cliente.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandonnavarro.microservicio_cliente.model.Cliente;
import brandonnavarro.microservicio_cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{codigoUnico}")
    public Mono<Cliente> getClientePorCodigoUnico(@PathVariable String codigoUnico, @RequestHeader("Tracking-ID") String trackingId) {  
        System.out.println("Tracking ID: " + trackingId);  
        return clienteService.getClientePorCodigoUnico(codigoUnico);
    }
    
}
