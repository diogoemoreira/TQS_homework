package TQS.Homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import io.restassured.RestAssured;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirQualityRestControllerIT {

    @LocalServerPort
    int port;

    @DisplayName("City exists then return aq forecast")
    @Test
    void whenCityExists(){
        RestAssured.given().port(port).get("/api/search/lisbon")
            .then()
            .statusCode(200)
            .and().body("name", equalTo("Entrecampos, Lisboa, Portugal"));
    }

    @DisplayName("City doesnt exist")
    @Test
    void whenCityDoesntExist(){
        RestAssured.given().port(port).get("/api/search/dsadsadsada")
                .then()
                .statusCode(404);
    }

    @DisplayName("Statistics")
    @Test
    void getStatistics(){
        RestAssured.given().port(port).get("/api/stats")
                .then()
                .statusCode(200)
                .and().body("Misses", equalTo(0))
                .and().body("Hits", equalTo(0))
                .and().body("No_Requests", equalTo(0));
    }

}
