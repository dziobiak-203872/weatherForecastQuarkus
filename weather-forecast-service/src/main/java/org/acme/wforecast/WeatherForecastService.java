package org.acme.wforecast;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@RegisterRestClient
public interface WeatherForecastService {

    @GET
    WeatherForecast getDailyForecastForCity(@QueryParam("city") String city);

    @GET
    Response getStatisticsForCity();
}
