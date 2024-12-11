package brandonnavarro.servicio_bff.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import brandonnavarro.servicio_bff.dto.ClienteDTO;
import brandonnavarro.servicio_bff.dto.ProductosDTO;
import brandonnavarro.servicio_bff.service.BffService;
import reactor.core.publisher.Mono;
import org.mockito.Mockito;

@WebFluxTest(BffController.class)
public class BffControllerTest {
    @Autowired
    private WebTestClient webTestClient; // Cliente para realizar solicitudes HTTP

    @MockitoBean
    private BffService bffService; // Mock del servicio dependiente

    @Test
    void testGetInformacionCliente() {
       
       String codigoUnico = "ABC123";

      
       ClienteDTO clienteDTO = new ClienteDTO();
       clienteDTO.setNombres("Juan");
       clienteDTO.setApellidos("Pérez");
       clienteDTO.setTipoDocumento("DNI");
       clienteDTO.setNumeroDocumento("12345678");
       clienteDTO.setProductosFinancieros(Arrays.asList(
           new ProductosDTO( "Cuentas de Ahorro", "Cuentas", 64599.64),
           new ProductosDTO( "Tarjeta Debito", "Tarjeta", 6897.5)
       ));

  
       Mockito.when(bffService.getInformacionCliente(codigoUnico))
           .thenReturn(Mono.just(clienteDTO));

     try {
       webTestClient.get()
                    .uri("/bff/informacion-cliente/{codigoUnico}", codigoUnico)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ClienteDTO.class)
                    .consumeWith(response -> {
                        ClienteDTO clienteResponse = response.getResponseBody();
                        assertNotNull(clienteResponse, "El cliente no debe ser nulo.");
                        assertEquals("Juan", clienteResponse.getNombres(), "El nombre del cliente no coincide.");
                        assertEquals("Pérez", clienteResponse.getApellidos(), "El apellido del cliente no coincide.");
                        assertEquals("DNI", clienteResponse.getTipoDocumento(), "El tipo de documento del cliente no coincide.");
                        assertEquals("12345678", clienteResponse.getNumeroDocumento(), "El número de documento del cliente no coincide.");

                        // Verificar que los productos financieros se hayan establecido correctamente
                        List<ProductosDTO> productos = clienteResponse.getProductosFinancieros();
                        assertNotNull(productos, "La lista de productos financieros no debe ser nula.");
                        assertEquals(2, productos.size(), "Se esperaban 2 productos en la lista.");
                        assertEquals("Cuentas de Ahorro", productos.get(0).getNombre(), "El nombre del primer producto no coincide.");
                        assertEquals("Cuentas", productos.get(0).getTipoProducto(), "El Tipo de Producto del primer producto no coincide.");
                        assertEquals(64599.64, productos.get(0).getSaldo(), "El saldo del primer producto no coincide.");
                        assertEquals("Tarjeta Debito", productos.get(1).getNombre(), "El nombre del segundo producto no coincide.");
                        assertEquals("Tarjeta", productos.get(1).getTipoProducto(), "El Tipo de Producto del segundo producto no coincide.");
                        assertEquals(6897.5, productos.get(1).getSaldo(), "El saldo del segundo producto no coincide.");
                    });
           System.out.println("Prueba pasada: BffController devuelve datos correctamente.");
       }catch (AssertionError e) {
          System.err.println("Prueba fallida: " + e.getMessage());
          throw e; // Re-lanzamos el error para que la prueba se registre como fallida.
      }
    }
}
