package RentaCarExercise.springboot.converters;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.model.Client;

public class ClientConverter {


    public static ClientCreateDto modelClientToClientDto(Client client) {
        return new ClientCreateDto(
                client.getName(),
                client.getEmail(),
                client.getDrivingLicense(),
                client.getNif()
        );
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
