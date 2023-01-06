package org.acme.jwt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class JwtResourceTest {

    @Test
    public void testGetJwt() {
        given()
                .when().get("/jwt")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("Mock Jwt token"));
    }
}
