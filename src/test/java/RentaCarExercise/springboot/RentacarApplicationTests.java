package RentaCarExercise.springboot;


import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.repositories.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RentacarApplicationTests {


    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        clientRepository.deleteAll();
        clientRepository.resetId();
    }

    @Test
    void contextLoads() {
    }


    @Test
    @DisplayName("Test get all clients when no clients on database returns empty list")
    void testGetAllClientsWhenNoClientsOnDatabaseReturnsEmpyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a client when client returns status code 201")
    public void testCreateClientReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String clientJson = "{\"name\": \"Joao\", \"email\": \"joao@teste.com\", \"drivingLicense\": \"234234551\", \"nif\": \"234234235\"}";


        //When
        MvcResult response = mockMvc.perform(post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        Client client = objectMapper.readValue(response.getResponse().getContentAsString(), Client.class);

        //assert student id and name using matchers
        Assert.isTrue(client.getId() == 1, "Client id is not 1");
        Assert.isTrue(client.getName().equals("Joao"), "Client name is not Joao");
        Assert.isTrue(client.getEmail().equals("joao@teste.com"), "Email is not joao@teste.com");
        Assert.isTrue(client.getNif() == 234234235, "Nif is not 234234235");
        Assert.isTrue(client.getDrivingLicense() == 234234551, "Driving License is not 234234551");

    }


    @Test
    @DisplayName("Test get all clients when 2 clients on database returns list with 2 clients")
    void testGetAllClientsWhen2ClientsOnDatabaseReturnsListWith2Clients() throws Exception {
        //Create 2 students
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Joao\", \"email\": \"joao@teste.com\", \"drivingLicense\": \"234234551\", \"nif\": \"234234235\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Maria\", \"email\": \"maria@teste.com\", \"drivingLicense\": \"234234552\", \"nif\": \"234234234\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/2"));
    }

}
