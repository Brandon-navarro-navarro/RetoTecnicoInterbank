package brandonnavarro.microservicio_cliente.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import brandonnavarro.microservicio_cliente.model.Cliente;
import brandonnavarro.microservicio_cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Mono<Cliente> getClientePorCodigoUnico(String codigoUnico){

        // String decryptedCodigoUnico = decryptCodigoUnico(codigoUnico);

        Optional<Cliente> client = clienteRepository.findByCodigoUnico(codigoUnico);
        return client.map(Mono::just).orElseGet(Mono::empty);
    }

    private String decryptCodigoUnico(String codigoUnico) {
        // Implementa la lógica de desencriptación
        return codigoUnico; // Retorno directo por simplicidad
    }
}
