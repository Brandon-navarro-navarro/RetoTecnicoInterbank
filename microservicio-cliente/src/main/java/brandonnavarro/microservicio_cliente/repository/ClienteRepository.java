package brandonnavarro.microservicio_cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brandonnavarro.microservicio_cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCodigoUnico(String codigoUnico);
}
