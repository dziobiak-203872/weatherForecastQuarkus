package org.acme.jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jwt")
@ApplicationScoped
public class JwtResource {

    @Inject
    JwtService jwtService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt() {
        return Response.ok(jwtService.generateJwt()).build();
    }
}
