package RentaCarExercise.springboot.services.clientService;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.expections.clientExpections.DeleteClientException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.clientExpections.UpdateClientException;

import java.util.List;

public interface ClientService {
    List<ClientCreateDto> getClients();

    void createClient(ClientCreateDto client) throws CreateClientException;

    ClientUpdateDto updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException;

    void deleteClient(Long id) throws DeleteClientException;

    Client getById(Long id) throws GetClientByIdException;
}
