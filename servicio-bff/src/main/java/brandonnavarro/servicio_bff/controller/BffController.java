package brandonnavarro.servicio_bff.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandonnavarro.servicio_bff.dto.ClienteDTO;
import brandonnavarro.servicio_bff.service.BffService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff")
public class BffController {
    
     private final BffService bffService;

    public BffController(BffService bffService) {
        this.bffService = bffService;
    }

    @GetMapping("/informacion-cliente/{codigoUnico}")
    public Mono<ClienteDTO> getInformacionCliente(@PathVariable String codigoUnico) {
       
        return bffService.getInformacionCliente(codigoUnico);
    }
}
