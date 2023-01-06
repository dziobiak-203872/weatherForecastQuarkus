package org.acme.wforecast;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

@QuarkusTest
public class WeatherForecastResourceTest {

    @InjectMock
    WeatherForecastResource weatherForecastResource;

    @Test
    public void testGetStatisticsForCityNoAuthorization() {
        given()
                .when().get("/stats")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin", "user"})
    public void testGetStatisticsForCityAuthorization() {
        Map<String, Integer> cityStats = new HashMap<>();
        cityStats.put("Warsaw", 2);
        cityStats.put("London", 1);

        Mockito.when(weatherForecastResource.getStatisticsForCity()).thenReturn(Response.ok(cityStats).build());
        given()
                .when().get("/stats")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("Warsaw", is(2))
                .body("London", is(1));
    }
}
