package org.acme.wforecast.jaxsupport;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class JaxRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.getHeaders().putIfAbsent("X_REQUESTID", Arrays.asList(UUID.randomUUID().toString()));
    }
}
