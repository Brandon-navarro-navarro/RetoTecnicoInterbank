package brandonnavarro.microservicio_cliente.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import brandonnavarro.microservicio_cliente.model.Cliente;
import brandonnavarro.microservicio_cliente.service.ClienteService;
import reactor.core.publisher.Mono;

@WebFluxTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private WebTestClient webTestClient; 

    @MockitoBean
    private ClienteService clienteService; 

    @Test
    void testGetClientePorCodigoUnico() {
    

        String codigoUnico = "codigo_encriptado";
        Cliente clienteMock = new Cliente(
            1, 
            "Juan", 
            "Perez", 
            "DNI", 
            "12345678", 
            "codigo_encriptado"
        );

       
        Mockito.when(clienteService.getClientePorCodigoUnico(codigoUnico))
               .thenReturn(Mono.just(clienteMock));

         try {
            webTestClient.get()
                     .uri("/api/cliente/{codigoUnico}", codigoUnico)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody(Cliente.class)
                     .value(cliente -> Assertions.assertAll(
                        () -> assertEquals(1, cliente.getId(), "El ID del cliente no coincide."),
                        () -> assertEquals("Juan", cliente.getNombres(), "El nombre del cliente no coincide."),
                        () -> assertEquals("Perez", cliente.getApellidos(), "El apellido del cliente no coincide."),
                        () -> assertEquals("DNI", cliente.getTipoDocumento(), "El tipo de documento no coincide."),
                        () -> assertEquals("12345678", cliente.getNumeroDocumento(), "El número de documento no coincide."),
                        () -> assertEquals("codigo_encriptado", cliente.getCodigoUnico(), "El código único no coincide.")
                    ));
            System.out.println("Prueba pasada: ClienteController devuelve datos correctamente.");
         }catch (AssertionError e) {
            System.err.println("Prueba fallida: " + e.getMessage());
            throw e; // Re-lanzamos el error para que la prueba se registre como fallida.
        }
    }
}
