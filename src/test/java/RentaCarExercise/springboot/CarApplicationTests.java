package RentaCarExercise.springboot;

import RentaCarExercise.springboot.dto.carDTO.CarGetDto;
import RentaCarExercise.springboot.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // This annotation is needed to autowire MockMvc
class CarApplicationTests {
    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void contextLoads() {
    }

    @AfterEach
    void deleteData() {
        carRepository.deleteAll();
        carRepository.resetId();
    }

    @Test
    @DisplayName("Test get all cars when 0 cars on database returns list with 0 cars")
    void testGetAllCarsWhen0CarsOnDatabaseReturnsListWith0Cars() throws Exception {
        mockMvc.perform(get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test get all cars when 2 cars on database returns list with 2 cars")
    void testGetAllCarsWhen2CarsOnDatabaseReturnsListWith2Cars() throws Exception {
        // GIVEN
        String car1Json = "{\"brand\": \"tesla\", \"plate\": \"AA-00-00\", \"km\": \"100\", \"horsePower\": \"1000\", \"pricePerDay\": \"10\"}";
        String car2Json = "{\"brand\": \"bmw\", \"plate\": \"AA-00-01\", \"km\": \"100\", \"horsePower\": \"1000\", \"pricePerDay\": \"10\"}";


        // WHEN
        mockMvc.perform(post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(car1Json));

        mockMvc.perform(post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(car2Json));

        // THEN
        mockMvc.perform(get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

    }


    @Test
    @DisplayName("Add a car to the database and check if id is 1 and status is 201")
    void testAddCarToDatabaseAndCheckIdAndCheck201Status() throws Exception {
        // GIVEN
        String carJSON = "{\"brand\": \"tesla\", \"plate\": \"AA-00-00\", \"km\": \"100\", \"horsePower\": \"1000\", \"pricePerDay\": \"10\"}";

        // WHEN
        MvcResult response = mockMvc.perform(post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJSON))
                .andExpect(status().isCreated())
                .andReturn();

        CarGetDto car = objectMapper.readValue(response.getResponse().getContentAsString(), CarGetDto.class);

        // THEN
        Assert.isTrue(car.id() == 1, "Car id is not 1");
        Assert.isTrue(car.brand().equals("tesla"), "Car brand is not tesla");
        Assert.isTrue(car.plate().equals("AA-00-00"), "Car license plate is not AA-00-00");
        Assert.isTrue(car.km() == 100, "Car km is not 100");
        Assert.isTrue(car.pricePerDay() == 10, "Car price per day is not 10");
        Assert.isTrue(car.horsePower() == 1000, "Car cylinder capacity is not 1000");

    }
}
