package org.acme.wforecast;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.wforecast.proxy.WeatherProvider;
import org.acme.wforecast.proxy.WeatherProviderProxy;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class WeatherForecastCacheService {

    private static final Logger LOGGER = Logger.getLogger(WeatherForecastCacheService.class);

    @Inject
    @RestClient
    WeatherProviderProxy weatherProviderProxy;

    @CacheResult(cacheName = "weather-cache")
    public WeatherForecast getDailyForecastForCityFromProvider(String city, String providerId) {
        LOGGER.info("///Retrieving weather data from external API///");
        WeatherProvider weatherProvider = weatherProviderProxy.getWeatherProviderById(providerId);

        StringBuilder baseUri = new StringBuilder(weatherProvider.url);
        baseUri.append(weatherProvider.params.get(0))
                .append(city)
                .append(weatherProvider.delimiter)
                .append(weatherProvider.apiKey);

        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(baseUri.toString()))
                .build(WeatherForecastService.class).getDailyForecastForCity(city);
    }
}
