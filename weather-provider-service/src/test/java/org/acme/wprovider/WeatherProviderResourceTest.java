package org.acme.wprovider;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class WeatherProviderResourceTest {

    @Test
    public void testGetWeatherProviderById() {
        String existingProvider = "weatherapi";
        given()
                .pathParam("providerId", existingProvider)
                .when().get("/api/providers/{providerId}")
                .then()
                .statusCode(200)
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
                .statusCode(204);
    }
}
