package org.acme.wforecast.citymapping;

import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class CityResource {

    private static final Logger LOGGER = Logger.getLogger(CityResource.class);

    @Inject
    @RestClient
    CityService cityService;

    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<City> getCityLocationData(@QueryParam("q") String city) {
        LOGGER.info("cityData: " + city);
        return cityService.getCityLocationData(city);
    }

}
