package brandonnavarro.microservicio_productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import brandonnavarro.microservicio_productos.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    List<Productos> findBycodigoUnicoCliente(String codigoUnicoCliente);
}
