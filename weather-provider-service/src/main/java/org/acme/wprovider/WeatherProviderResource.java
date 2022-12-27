package org.acme.wprovider;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/api/providers")
public class WeatherProviderResource {

    @Inject
    Logger logger;

    @GET
    @Path("/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherProvider getWeatherProviderByName(@PathParam String providerId)
            throws IOException, URISyntaxException {
        logger.info("Accessing data provider with name: " + providerId);

        ObjectMapper mapper = new ObjectMapper();
        URL resource = getClass().getClassLoader().getResource("providers.json");
        File jsonFile = new File(resource.toURI());
        Set<WeatherProvider> weatherProviders = mapper.readValue(jsonFile,
                mapper.getTypeFactory().constructCollectionType(Set.class, WeatherProvider.class));

        logger.info("Weather provider data: " + weatherProviders);

        return weatherProviders.stream().filter(provider -> provider.name.equals(providerId))
                .findFirst().orElse(null);
    }
}
