package RentaCarExercise.springboot.services.clientService;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientGetDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.expections.clientExpections.DeleteClientException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.clientExpections.UpdateClientException;
import RentaCarExercise.springboot.mapper.ClientMapper;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.repositories.ClientRepository;
import RentaCarExercise.springboot.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientsRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientGetDto> getClients() {
        return clientMapper.modelClientToGetClientDto(clientsRepository.findAll());
    }

    @Override
    public void createClient(ClientCreateDto client) throws CreateClientException {
        Optional<Client> clientOptional = clientsRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent()) {
            throw new CreateClientException(Messages.EMAIL_IN_USE);
        }
        if (clientsRepository.findByNif(client.nif()).isPresent()) {
            throw new CreateClientException(Messages.EMAIL_IN_USE); //todo have to change messsage
        }
        Client newClient = clientMapper.clientDtoToModelClient(client);
        clientsRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException {
        Client clientToUpdate = clientsRepository.findById(id).orElseThrow(() -> new UpdateClientException(Messages.ID_DOES_NOT_EXIST));
        if (clientsRepository.findByEmail(clientDto.email()).isEmpty()) { //todo FIX THE EMPTY TO PRESENT
            throw new UpdateClientException(Messages.EMAIL_IN_USE);
        }
        clientToUpdate.setEmail(clientDto.email());
        clientToUpdate.setName(clientDto.name());
        clientsRepository.save(clientToUpdate);
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
