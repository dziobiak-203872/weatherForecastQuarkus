package org.acme.jwt;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class JwtResourceTest {

    @InjectMock
    JwtService jwtService;

    @Test
    public void testGetJwt() {
        Mockito.when(jwtService.generateJwt()).thenReturn("Mock Jwt token");
        given()
                .when().get("/jwt")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("Mock Jwt token"));
    }
}
