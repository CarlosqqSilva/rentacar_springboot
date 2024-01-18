package RentaCarExercise.springboot.controllers;

import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientGetDto;
import RentaCarExercise.springboot.dto.clientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.expections.clientExpections.DeleteClientException;
import RentaCarExercise.springboot.expections.clientExpections.UpdateClientException;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.services.clientService.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientServiceImpl clientsService;

    @Autowired
    public ClientController(ClientServiceImpl clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientGetDto>> getClients() {
        return ResponseEntity.ok(clientsService.getClients());
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody ClientCreateDto client) throws CreateClientException {
        Client client1 = clientsService.createClient(client);
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody ClientUpdateDto client, @PathVariable long id) throws UpdateClientException {
        clientsService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) throws DeleteClientException {
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
