package brandonnavarro.servicio_bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDTO {
    private String nombre;
    private String tipoProducto;
    private Double saldo;
}
