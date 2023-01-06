package org.acme.wforecast;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;

import javax.ws.rs.core.Response;

@QuarkusTest
public class WeatherForecastResourceStatsTest {

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
        given()
                .when().get("/stats")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("Warsaw", is(2))
                .body("London", is(1));
    }
}
