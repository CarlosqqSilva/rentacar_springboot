package RentaCarExercise.springboot.Services.ClientService;

import RentaCarExercise.springboot.Converter.ClientConverter;
import RentaCarExercise.springboot.Dto.ClientDTO.ClientCreateDto;
import RentaCarExercise.springboot.Dto.ClientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.Entity.Client;
import RentaCarExercise.springboot.Expections.ClientExpections.CreateClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.DeleteClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.GetClientByIdException;
import RentaCarExercise.springboot.Expections.ClientExpections.UpdateClientException;
import RentaCarExercise.springboot.Mapper.ClientMapper;
import RentaCarExercise.springboot.Repositories.ClientRepository;
import RentaCarExercise.springboot.Util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static RentaCarExercise.springboot.Converter.ClientConverter.fromDtoToEntity;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientsRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
//    public List<ClientCreateDto> getClients() {
//        List<Client> clients = clientsRepository.findAll();
//        return clients.stream()
//                .map(ClientConverter::fromEntityClientToDto)
//                .toList();
//    }

    public List<ClientCreateDto> getClients() {
        List<Client> client = new ArrayList<>();
        client.addAll(clientsRepository.findAll());
        return ClientMapper.INSTANCE.EntityClientToClientDto(client);
    }

    @Override
    public void createClient(ClientCreateDto client) throws CreateClientException {
        Optional<Client> clientOptional = clientsRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent()) {
            throw new CreateClientException(Messages.EMAIL_IN_USE);
        }
        Client newClient = fromDtoToEntity(client);
        clientsRepository.save(newClient);
    }

    @Override
    public ClientUpdateDto updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException {
        Client existingClient = clientsRepository.getReferenceById(id);
        if (existingClient == null) {
            throw new UpdateClientException(Messages.USER_NOT_FOUND);
        }
        if (existingClient.getName().isEmpty()) {
            existingClient.setName(clientDto.name());
        }
        if (existingClient.getEmail().isEmpty()) {
            existingClient.setEmail(clientDto.email());
        }

        Client updateClient = clientsRepository.save(existingClient);
        return ClientConverter.fromEntityClientUpdateToDto(updateClient);
    }

    @Override
    public void deleteClient(Long id) throws DeleteClientException {
        Client client = clientsRepository.getReferenceById(id);
        if (client == null) {
            throw new DeleteClientException(Messages.USER_NOT_FOUND);
        }
        clientsRepository.delete(client);
    }

    public Client getById(Long id) throws GetClientByIdException {
        Optional<Client> clientOptional = this.clientsRepository.findById(id);
        if (clientOptional.isEmpty())
            throw new GetClientByIdException(Messages.ID_DOES_NOT_EXIST);
        return clientOptional.get();
    }
}
