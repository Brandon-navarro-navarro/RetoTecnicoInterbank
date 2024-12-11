package brandonnavarro.microservicio_productos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandonnavarro.microservicio_productos.dto.ProductosDTO;
import brandonnavarro.microservicio_productos.mapper.ProductosMapper;
import brandonnavarro.microservicio_productos.service.ProductosService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductosController {

    private final ProductosService productosService;
    private final ProductosMapper productosMapper;

    @GetMapping("/{codigoUnico}")
    public Flux<ProductosDTO> getProductossByCodigoUnico(@PathVariable String codigoUnico, @RequestHeader("Tracking-ID") String trackingId) {
        System.out.println("Tracking ID: " + trackingId);  
        return productosService.getProductosCodigoUnico(codigoUnico)
                .map(productosMapper::toDTO);
    }
    

}
