package RentaCarExercise.springboot.services.clientService;

import RentaCarExercise.springboot.converters.ClientConverter;
import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.expections.clientExpections.DeleteClientException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.clientExpections.UpdateClientException;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.repositories.ClientRepository;
import RentaCarExercise.springboot.util.Messages;
import RentaCarExercise.springboot.util.MessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientCreateDto getClients() {
        return ClientConverter.modelClientToClientDto((Client) clientRepository.findAll());
    }

    @Override
    public Client createClient(ClientCreateDto client) throws CreateClientException {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent()) {
            throw new CreateClientException(MessagesEnum.EMAIL_IN_USE.getMessage());
        }
        if (clientRepository.findByNif(client.nif()).isPresent()) {
            throw new CreateClientException(Messages.NIF_ALREADY_IN_USE);
        }
        Client newClient = ClientConverter.clientDtoToModelClient(client);
        return clientRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(() -> new UpdateClientException(Messages.ID_DOES_NOT_EXIST));
        if (clientRepository.findClientByEmail(clientDto.email()).isPresent()) {
            throw new UpdateClientException(Messages.EMAIL_IN_USE);
        }
        clientToUpdate.setEmail(clientDto.email());
        clientToUpdate.setName(clientDto.name());
        clientRepository.save(clientToUpdate);
    }

    @Override
    public void deleteClient(Long id) throws DeleteClientException {
        Client client = clientRepository.getReferenceById(id);
        if (client == null) {
            throw new DeleteClientException(Messages.USER_NOT_FOUND);
        }
        clientRepository.delete(client);
    }

    public Client getById(Long id) throws GetClientByIdException {
        Optional<Client> clientOptional = this.clientRepository.findById(id);
        if (clientOptional.isEmpty())
            throw new GetClientByIdException(Messages.ID_DOES_NOT_EXIST);
        return clientOptional.get();
    }
}
