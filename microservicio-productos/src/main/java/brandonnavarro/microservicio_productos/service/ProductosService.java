package brandonnavarro.microservicio_productos.service;


import org.springframework.stereotype.Service;

import brandonnavarro.microservicio_productos.model.Productos;
import brandonnavarro.microservicio_productos.repository.ProductosRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductosService {
    private final ProductosRepository productosRepository;

    public Flux<Productos> getProductosCodigoUnico(String codigoUnicoCliente) {
        return Flux.fromIterable(productosRepository.findBycodigoUnicoCliente(codigoUnicoCliente));
    }


}
