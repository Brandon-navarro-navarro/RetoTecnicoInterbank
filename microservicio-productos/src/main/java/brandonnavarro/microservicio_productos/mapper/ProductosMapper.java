package brandonnavarro.microservicio_productos.mapper;

import org.mapstruct.Mapper;

import brandonnavarro.microservicio_productos.dto.ProductosDTO;
import brandonnavarro.microservicio_productos.model.Productos;

@Mapper(componentModel = "spring")
public interface ProductosMapper {
    ProductosDTO toDTO(Productos productos);
    Productos toEntidad(ProductosDTO productoDTO);
}
