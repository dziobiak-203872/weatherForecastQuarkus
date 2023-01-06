package org.acme.wprovider;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;

import javax.ws.rs.core.Response;

@QuarkusTest
public class WeatherProviderResourceTest {

    @Test
    public void testGetWeatherProviderById() {
        String existingProvider = "weatherapi";
        given()
                .pathParam("providerId", existingProvider)
                .when().get("/api/providers/{providerId}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("url", is("http://api.weatherapi.com/v1/current.json?"))
                .body("$", hasKey("apiKey"))
                .body("params", hasSize(1))
                .body("delimiter", is(","));
    }

    @Test
    public void testGetWeatherProviderByIdNegative() {
        String nonExistentProvider = "nonexistentapi";
        given()
                .pathParam("providerId", nonExistentProvider)
                .when().get("/api/providers/{providerId}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}
