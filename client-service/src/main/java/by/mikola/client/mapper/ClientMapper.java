package by.mikola.client.mapper;

import by.mikola.client.dto.ClientDTO;
import by.mikola.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {
    Client toEntity(ClientDTO dto);

    ClientDTO toDto(Client client);

}
