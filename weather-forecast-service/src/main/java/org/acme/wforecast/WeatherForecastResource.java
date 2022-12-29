package org.acme.wforecast;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.jboss.logging.Logger;

@Path("/")
@ClientHeaderParam(name = "X_REQUESTID", value = "{org.acme.wforecast.CustomHeaderHelper.getHeaderValue}")
public class WeatherForecastResource {

    private static final Logger LOGGER = Logger.getLogger(WeatherForecastResource.class);

    @Inject
    WeatherForecastCacheService weatherForecastCacheService;

    @GET
    @Path("/{city}/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public <T> T getDailyForecastForCity(@QueryParam("city") String city, @QueryParam("providerId") String providerId) {
        LOGGER.info("PROVIDER_ID: " + providerId + " CITY: " + city);
        return weatherForecastCacheService.getDailyForecastForCityFromProvider(city, providerId);
    }
}
