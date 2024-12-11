package brandonnavarro.microservicio_cliente.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandonnavarro.microservicio_cliente.model.Cliente;
import brandonnavarro.microservicio_cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;


    @GetMapping("/{codigoUnico}")
    public Mono<Cliente> getMethodName(@PathVariable String codigoUnico) {    
        return clienteService.getClientePorCodigoUnico(codigoUnico);
    }
    

}
