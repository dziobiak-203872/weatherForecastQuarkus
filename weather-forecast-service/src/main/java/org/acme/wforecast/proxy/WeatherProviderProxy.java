package org.acme.wforecast.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/providers")
@RegisterRestClient(configKey = "wprovider.proxy")
public interface WeatherProviderProxy {

    @GET
    @Path("/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    WeatherProvider getWeatherProviderByName(@PathParam("providerId") String providerId);
}
