package RentaCarExercise.springboot.mapper;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientDtoToEntityClient(ClientCreateDto clientCreateDto);

    ClientCreateDto EntityClientToClientDto(Client client);

    List<ClientCreateDto> EntityClientToClientDto(List<Client> client);

    List<Client> clientDtoToEntityClient(List<ClientCreateDto> clientCreateDto);
}
