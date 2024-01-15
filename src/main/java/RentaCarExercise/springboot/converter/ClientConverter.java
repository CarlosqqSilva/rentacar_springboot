package RentaCarExercise.springboot.converter;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.model.Client;

public class ClientConverter {

    public static ClientCreateDto fromModelClientToDto(Client clients) {
        return ClientCreateDto.builder()
                .name(clients.getName())
                .email(clients.getEmail())
                .dateOfBirth(clients.getDateOfBirth())
                .id(clients.getId())
                .drivingLicense(clients.getDrivingLicense())
                .nif(clients.getNif())
                .build();
    }

    public static ClientUpdateDto fromModelClientUpdateToDto(Client clients) {
        return ClientUpdateDto.builder()
                .name(clients.getName())
                .email(clients.getEmail())
                .build();
    }

    public static Client fromDtoToModel(ClientCreateDto clientDto) {

        return new Client(
                clientDto.name(),
                clientDto.email(),
                clientDto.drivingLicense(),
                clientDto.dateOfBirth(),
                clientDto.nif()
        );
    }
}
