package org.acme.wforecast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

@Path("/")
public class WeatherForecastResource {

    private static final Logger LOGGER = Logger.getLogger(WeatherForecastResource.class);
    private Map<String, Integer> cityStats = Collections.synchronizedMap(new HashMap<>());

    @Inject
    WeatherForecastCacheService weatherForecastCacheService;

    @GET
    @Path("/{city}/{providerId}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public <T> T getDailyForecastForCity(@QueryParam("city") String city, @QueryParam("providerId") String providerId) {
        LOGGER.info("PROVIDER_ID: " + providerId + " CITY: " + city);
        cityStats.merge(city, 1, Integer::sum);
        return weatherForecastCacheService.getDailyForecastForCityFromProvider(city, providerId);
    }

    @GET
    @Path("/stats")
    @RolesAllowed({"admin", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsForCity() {
        return Response.ok(cityStats).build();
    }
}
