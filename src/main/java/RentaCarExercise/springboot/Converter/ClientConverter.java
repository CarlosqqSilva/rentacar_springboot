package RentaCarExercise.springboot.Converter;

import RentaCarExercise.springboot.Dto.ClientDTO.ClientCreateDto;
import RentaCarExercise.springboot.Dto.ClientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.Entity.Client;

public class ClientConverter {

    public static ClientCreateDto fromEntityClientToDto(Client clients) {
        return ClientCreateDto.builder()
                .name(clients.getName())
                .email(clients.getEmail())
                .dateOfBirth(clients.getDateOfBirth())
                .id(clients.getId())
                .drivingLicense(clients.getDrivingLicense())
                .nif(clients.getNif())
                .build();
    }

    public static ClientUpdateDto fromEntityClientUpdateToDto(Client clients) {
        return ClientUpdateDto.builder()
                .name(clients.getName())
                .email(clients.getEmail())
                .build();
    }

    public static Client fromDtoToEntity(ClientCreateDto clientDto) {

        return new Client(
                clientDto.name(),
                clientDto.email(),
                clientDto.drivingLicense(),
                clientDto.dateOfBirth(),
                clientDto.nif()
        );
    }
}
