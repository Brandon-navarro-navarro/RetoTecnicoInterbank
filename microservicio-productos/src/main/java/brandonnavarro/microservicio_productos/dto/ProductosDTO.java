package brandonnavarro.microservicio_productos.dto;

import lombok.Data;

@Data
public class ProductosDTO {
    private String tipoProducto;
    private String nombre;
    private Double saldo;
}
