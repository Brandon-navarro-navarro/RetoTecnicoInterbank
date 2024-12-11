package brandonnavarro.microservicio_productos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDTO {
    private String tipoProducto;
    private String nombre;
    private Double saldo;
}
