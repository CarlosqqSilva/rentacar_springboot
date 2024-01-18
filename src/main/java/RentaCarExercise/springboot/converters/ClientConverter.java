package RentaCarExercise.springboot.converters;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientGetDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.model.Client;

import java.util.List;

public class ClientConverter {


    public static ClientCreateDto modelClientToClientDto(Client client) {
        return new ClientCreateDto(
                client.getName(),
                client.getEmail(),
                client.getDrivingLicense(),
                client.getNif()
        );
    }

    public static ClientGetDto fromClientModelToGetDto(Client client) {
        return new ClientGetDto(
                client.getName(),
                client.getEmail(),
                client.getNif(),
                client.getDrivingLicense()
        );
    }

    public static List<ClientGetDto> fromClientModelListToGetDtoList(List<Client> clients) {
        return clients.stream()
                .map(ClientConverter::fromClientModelToGetDto)
                .toList();
    }

    public static ClientUpdateDto modelClientUpdateToDto(Client client) {
        return new ClientUpdateDto(
                client.getName(),
                client.getEmail()
        );
    }

    public static Client clientDtoToModelClient(ClientCreateDto clientCreateDto) {
        return new Client(
                clientCreateDto.name(),
                clientCreateDto.email(),
                clientCreateDto.drivingLicense(),
                clientCreateDto.nif()
        );
    }
}
