package RentaCarExercise.springboot;

import RentaCarExercise.springboot.mapper.ClientMapper;
import RentaCarExercise.springboot.repositories.ClientRepository;
import RentaCarExercise.springboot.services.clientService.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientServiceTests {
    static MockedStatic<ClientMapper> mockedClientMapper = Mockito.mockStatic(ClientMapper.class);
    @MockBean
    private ClientRepository clientRepositoryMock;
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {

        clientService = new ClientServiceImpl(clientRepositoryMock);
    }
}
