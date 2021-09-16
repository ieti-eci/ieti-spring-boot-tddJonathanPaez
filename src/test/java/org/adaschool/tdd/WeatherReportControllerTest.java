import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

//@WebMvcTest(WeatherReportController.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class WeatherReportControllerTest {
    @LocalServerPort
    private int port;
    /*
        @Autowired
        MockMvc mockMvc;
        @Autowired
        ObjectMapper mapper;
        @MockBean
        WeatherReportRepository repository;

        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation( lat, lng );

        WeatherReport record1 = new WeatherReport("1",location, 35f, 20f, "tester", new Date() );
        WeatherReport record2 = new WeatherReport("avc2",location, 36f, 12f, "admin", new Date() );
        WeatherReport record3 = new WeatherReport("avc3",location, 38f, 92f, "prueba", new Date() );

    */
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void weatherControllerGetByID() {
        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation( lat, lng );
        WeatherReportDto dto=new WeatherReportDto( location, 35f, 22f, "tester", new Date() );
        ResponseEntity<WeatherReportDto> post = restTemplate.postForEntity("http://localhost:" + port + "/v1/weather",dto,WeatherReportDto.class);
        ResponseEntity<WeatherReportDto> request = restTemplate.getForEntity("http://localhost:" + port + "/v1/weather/1",WeatherReportDto.class);
        Assertions.assertEquals(500, request.getStatusCodeValue());
    }
    @Test
    public void weatherControllerGetByNameReporter() {
        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation( lat, lng );
        WeatherReportDto dto=new WeatherReportDto( location, 35f, 22f, "tester", new Date() );
        ResponseEntity<WeatherReportDto> post = restTemplate.postForEntity("http://localhost:" + port + "/v1/weather",dto,WeatherReportDto.class);
        ResponseEntity<WeatherReportDto> request = restTemplate.getForEntity("http://localhost:" + port + "/v1/weather/reporter/tester",WeatherReportDto.class);
        Assertions.assertEquals(500, request.getStatusCodeValue());
    }

    @Test
    public void weatherControllerPostTest()
            throws Exception
    {
        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation( lat, lng );
        WeatherReportDto dto=new WeatherReportDto( location, 35f, 22f, "tester", new Date() );

        ResponseEntity<WeatherReportDto> request = restTemplate.postForEntity("http://localhost:" + port + "/v1/weather",dto,WeatherReportDto.class);
        //System.out.println("POST executed");
        //System.out.println("POST StatusCode = " + request.getStatusCode());
        Assertions.assertEquals(request.getBody().getReporter(),"tester");
    }



    @Test
    public void greetingShouldReturnDefaultMessage()
            throws Exception
    {
        assertThat(
                this.restTemplate.getForObject( "http://localhost:" + port + "/v1/health", String.class ) ).contains(
                "API Working OK!" );
    }

}
