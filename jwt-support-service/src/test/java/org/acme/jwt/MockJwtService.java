package org.acme.jwt;

import javax.inject.Singleton;

import io.quarkus.test.Mock;

@Mock
@Singleton
public class MockJwtService extends JwtService {

    @Override
    public String generateJwt() {
        return "Mock Jwt token";
    }
}
