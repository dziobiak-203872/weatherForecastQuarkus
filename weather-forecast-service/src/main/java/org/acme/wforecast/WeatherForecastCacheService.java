package org.acme.wforecast;

import java.net.URI;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.wforecast.citymapping.City;
import org.acme.wforecast.citymapping.CityService;
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
    CityService cityService;

    @Inject
    @RestClient
    WeatherProviderProxy weatherProviderProxy;

    @CacheResult(cacheName = "weather-cache")
    WeatherForecast getDailyForecastForCityFromProvider(String cityName, String cityState, String countryCode, String providerId) {
        LOGGER.info("///Retrieving weather data from external API///");
        WeatherProvider weatherProvider = weatherProviderProxy.getWeatherProviderById(providerId);

        City city = cityService.getCityLocationData(String.join(",", Arrays.asList(cityName, cityState, countryCode))).stream().findFirst().get();

        StringBuilder baseUri = new StringBuilder(weatherProvider.url);
        if (weatherProvider.params.size() == 1) {
            baseUri.append(weatherProvider.params.get(0))
                    .append(city.latitude)
                    .append(weatherProvider.delimiter)
                    .append(city.longitude)
                    .append(weatherProvider.apiKey);
        } else {
            baseUri.append(weatherProvider.params.get(0))
                    .append(city.latitude)
                    .append(weatherProvider.delimiter)
                    .append(weatherProvider.params.get(1))
                    .append(city.longitude)
                    .append(weatherProvider.apiKey);
        }

        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(baseUri.toString()))
                .build(WeatherForecastService.class).getDailyForecastForCity();
    }
}
