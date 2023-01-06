package org.acme.jwt;

import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class JwtService {

    public String generateJwt() {
        return Jwt.issuer("jwt-support-service")
                .subject("jwt-support-service")
                .groups(new HashSet<>(Arrays.asList("admin", "user")))
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }
}
