package RentaCarExercise.springboot.Services.ClientService;

import RentaCarExercise.springboot.Dto.ClientDTO.ClientCreateDto;
import RentaCarExercise.springboot.Dto.ClientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.Entity.Client;
import RentaCarExercise.springboot.Expections.ClientExpections.CreateClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.DeleteClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.GetClientByIdException;
import RentaCarExercise.springboot.Expections.ClientExpections.UpdateClientException;

import java.util.List;

public interface ClientService {
    List<ClientCreateDto> getClients();

    void createClient(ClientCreateDto client) throws CreateClientException;

    ClientUpdateDto updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException;

    void deleteClient(Long id) throws DeleteClientException;

    Client getById(Long id) throws GetClientByIdException;
}
