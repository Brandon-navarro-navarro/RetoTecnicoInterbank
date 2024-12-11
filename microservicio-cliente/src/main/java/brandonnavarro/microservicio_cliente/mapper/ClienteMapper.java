package brandonnavarro.microservicio_cliente.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import brandonnavarro.microservicio_cliente.dto.ClienteDTO;
import brandonnavarro.microservicio_cliente.model.Cliente;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCIA = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteToClienteDTO(Cliente cliente);
    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);


}
