package org.acme.wprovider;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/api/providers")
public class WeatherProviderResource {

    private static final Logger LOGGER = Logger.getLogger(WeatherProviderResource.class);

    @GET
    @Path("/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherProvider getWeatherProviderById(@PathParam("providerId") String providerId)
            throws IOException, URISyntaxException {
        LOGGER.info("Accessing provider data with id: " + providerId);

        ObjectMapper mapper = new ObjectMapper();
        URL resource = getClass().getClassLoader().getResource("providers.json");
        File jsonFile = new File(resource.toURI());
        Set<WeatherProvider> weatherProviders = mapper.readValue(jsonFile,
                mapper.getTypeFactory().constructCollectionType(Set.class, WeatherProvider.class));

        LOGGER.info("Weather providers data: " + weatherProviders);

        return weatherProviders.stream().filter(provider -> provider.providerId.equals(providerId))
                .findFirst().orElse(null);
    }
}
