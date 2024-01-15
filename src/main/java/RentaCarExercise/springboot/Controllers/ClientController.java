package RentaCarExercise.springboot.Controllers;

import RentaCarExercise.springboot.Dto.ClientDTO.ClientCreateDto;
import RentaCarExercise.springboot.Dto.ClientDTO.ClientUpdateDto;
import RentaCarExercise.springboot.Entity.Client;
import RentaCarExercise.springboot.Expections.ClientExpections.CreateClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.DeleteClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.UpdateClientException;
import RentaCarExercise.springboot.Services.ClientService.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<ClientCreateDto>> getClients() {

        return ResponseEntity.ok(clientsService.getClients());
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody ClientCreateDto client) throws CreateClientException {
        clientsService.createClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientUpdateDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientUpdateDto clientDto, BindingResult bindingResult) throws UpdateClientException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ClientUpdateDto savedClient = clientsService.updateClient(id, clientDto);
        return new ResponseEntity<>(savedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) throws DeleteClientException {
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
