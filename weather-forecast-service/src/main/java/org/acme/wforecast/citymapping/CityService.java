package org.acme.wforecast.citymapping;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "geocoding-api")
@ApplicationScoped
public interface CityService {

    @GET
    ArrayList<City> getCityLocationData(@QueryParam("q") String cityData);
}
