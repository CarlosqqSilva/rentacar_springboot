package RentaCarExercise.springboot.mapper;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientGetDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientDtoToModelClient(ClientCreateDto clientCreateDto);

    ClientCreateDto modelClientToClientDto(Client client);

    List<ClientCreateDto> modelClientToClientDto(List<Client> client);

    List<Client> clientDtoToModelClient(List<ClientCreateDto> clientCreateDto);

    ClientUpdateDto modelClientUpdateToDto(Client clients);

    List<ClientGetDto> modelClientToGetClientDto(List<Client> client);
}
