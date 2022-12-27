package org.acme.wforecast;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.acme.wforecast.proxy.WeatherProvider;
import org.acme.wforecast.proxy.WeatherProviderProxy;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.net.URI;

@Path("/")
public class WeatherForecastResource {

    @Inject
    Logger logger;

    @Inject
    @RestClient
    WeatherProviderProxy weatherProviderProxy;

    private WeatherForecastService weatherForecastService;

    @GET
    @Path("/{city}/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public <T> T getDailyForecastForCity(@PathParam String city, @PathParam String providerId) {
        logger.info("PROVIDER_ID: " + providerId + " CITY: " + city);
        WeatherProvider weatherProvider = weatherProviderProxy.getWeatherProviderByName(providerId);
        weatherForecastService = RestClientBuilder.newBuilder()
                .baseUri(URI.create(weatherProvider.url + city + weatherProvider.apiKey))
                .build(WeatherForecastService.class);
        return weatherForecastService.getDailyForecastForCity(city);
    }
}
