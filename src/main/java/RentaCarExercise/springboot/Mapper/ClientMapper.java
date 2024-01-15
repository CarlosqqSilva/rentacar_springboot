package RentaCarExercise.springboot.Mapper;

import RentaCarExercise.springboot.Dto.ClientDTO.ClientCreateDto;
import RentaCarExercise.springboot.Entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientDtoToEntityClient(ClientCreateDto clientCreateDto);

    ClientCreateDto EntityClientToClientDto(Client client);

    List<ClientCreateDto> EntityClientToClientDto(List<Client> client);

    List<Client> clientDtoToEntityClient(List<ClientCreateDto> clientCreateDto);
}
