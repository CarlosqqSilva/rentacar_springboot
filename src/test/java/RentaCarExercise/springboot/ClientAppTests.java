package RentaCarExercise.springboot;

import RentaCarExercise.springboot.converters.ClientConverter;
import RentaCarExercise.springboot.dto.clientDTO.ClientCreateDto;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.repositories.ClientRepository;
import RentaCarExercise.springboot.services.clientService.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ClientAppTests {

    static MockedStatic<ClientConverter> mockedClientConverter = Mockito.mockStatic(ClientConverter.class);
    @MockBean
    private ClientRepository clientRepositoryMock;
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {

        clientService = new ClientServiceImpl(clientRepositoryMock);
    }


    @Test
    void testCreateClientCallsRepositoryAndConverter() throws CreateClientException {
        //given
        ClientCreateDto clientCreateDto = new ClientCreateDto("Diogo", "diogo@test.com", 234234235, 235236237);
        Client client = new Client("Diogo", "diogo@test.com", 234234235, 235236237);
        when(clientRepositoryMock.findClientByEmail(clientCreateDto.email())).thenReturn(Optional.empty());
        mockedClientConverter.when(() -> ClientConverter.clientDtoToModelClient(clientCreateDto)).thenReturn(client);
        when(clientRepositoryMock.save(Mockito.any())).thenReturn(client);

        //when
        clientService.createClient(clientCreateDto);


        //then
        verify(clientRepositoryMock, times(1)).findClientByEmail(clientCreateDto.email());

        mockedClientConverter.verify(() -> ClientConverter.clientDtoToModelClient(clientCreateDto));
        mockedClientConverter.verifyNoMoreInteractions();

        verify(clientRepositoryMock, times(1)).save(client);
        Mockito.verifyNoMoreInteractions(clientRepositoryMock);
        assertEquals(client, clientService.createClient(clientCreateDto));
    }

    @Test
    void createClientWithDuplicatedEmailThrowsException() {
        //given
        ClientCreateDto clientCreateDto = new ClientCreateDto("Diogo", "diogo@test.com", 234234235, 235236237);

        //when
        when(clientRepositoryMock.findClientByEmail(clientCreateDto.email())).thenReturn(Optional.of(new Client()));
        //then
        assertThrows(IllegalStateException.class, () -> clientService.createClient(clientCreateDto));
        assertEquals("email taken", assertThrows(IllegalStateException.class, () -> clientService.createClient(clientCreateDto)).getMessage());

    }
}
