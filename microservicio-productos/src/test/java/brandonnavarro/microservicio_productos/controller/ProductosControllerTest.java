package brandonnavarro.microservicio_productos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import brandonnavarro.microservicio_productos.dto.ProductosDTO;
import brandonnavarro.microservicio_productos.mapper.ProductosMapper;
import brandonnavarro.microservicio_productos.model.Productos;
import brandonnavarro.microservicio_productos.service.ProductosService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(ProductosController.class)
public class ProductosControllerTest {
    @Autowired
    private WebTestClient webTestClient; 

    @MockitoBean
    private ProductosService productosService; 

    @MockitoBean
    private ProductosMapper productosMapper; // Mock del mapper dependiente



    @Test
    void testGetProductosCodigoUnico() {
    

        String codigoUnico = "codigo_encriptado";
        String trackingId = "tracking123";
        Productos productoMock = new Productos(
            1, 
            "Cuenta", 
            "Cuenta Corriente", 
            326465.36, 
            "codigo_encriptado"
        );


        ProductosDTO productoDTO = new ProductosDTO(
            "Cuenta", 
            "Cuenta Corriente", 
            326465.36
        );
       
    
        Mockito.when(productosService.getProductosCodigoUnico(codigoUnico))
               .thenReturn(Flux.just(productoMock));

  
        Mockito.when(productosMapper.toDTO(productoMock))
               .thenReturn(productoDTO);

         try {
            webTestClient.get()
                     .uri("/api/productos/{codigoUnico}", codigoUnico)
                     .header("Tracking-ID", trackingId) 
                     .exchange()
                     .expectStatus().isOk()
                     .expectBodyList(ProductosDTO.class)
                     .consumeWith(response -> {
                         List<ProductosDTO> productos = response.getResponseBody();
                         assertNotNull(productos, "La lista de productos no debe ser nula.");
                         assertEquals(1, productos.size(), "Se esperaba un producto en la respuesta.");
                         assertEquals("Cuenta Corriente", productos.get(0).getNombre(), "El nombre del producto no coincide.");
                         assertEquals("Cuenta", productos.get(0).getTipoProducto(), "El tipo de producto del producto no coincide.");
                         assertEquals(326465.36, productos.get(0).getSaldo(), "El saldo del producto no coincide.");
                     });
                    
            System.out.println("Prueba pasada: ProductosController devuelve datos correctamente.");
         }catch (AssertionError e) {
            System.err.println("Prueba fallida: " + e.getMessage());
            throw e; 
        }
    }
}
