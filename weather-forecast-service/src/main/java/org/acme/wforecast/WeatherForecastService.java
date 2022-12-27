package org.acme.wforecast;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;

@RegisterRestClient
public interface WeatherForecastService {

    @GET
    <T> T getDailyForecastForCity(@QueryParam String city);
}
