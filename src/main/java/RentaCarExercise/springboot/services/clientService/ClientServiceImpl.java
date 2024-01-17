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
import RentaCarExercise.springboot.util.MessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientGetDto> getClients() {
        return clientMapper.modelClientToGetClientDto(clientRepository.findAll());
    }

    @Override
    public Client createClient(ClientCreateDto client) throws CreateClientException {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent()) {
            throw new CreateClientException(MessagesEnum.EMAIL_IN_USE.getMessage());
        }
        if (clientRepository.findByNif(client.nif()).isPresent()) {
            throw new CreateClientException(Messages.EMAIL_IN_USE); //todo have to change messsage
        }
        Client newClient = clientMapper.clientDtoToModelClient(client);
        return clientRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientUpdateDto clientDto) throws UpdateClientException {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(() -> new UpdateClientException(Messages.ID_DOES_NOT_EXIST));
        if (clientRepository.findByEmail(clientDto.email()).isEmpty()) { //todo FIX THE EMPTY TO PRESENT
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
