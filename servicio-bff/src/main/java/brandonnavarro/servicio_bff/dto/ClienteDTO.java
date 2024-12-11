package brandonnavarro.servicio_bff.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private List<ProductosDTO> productosFinancieros;
}
