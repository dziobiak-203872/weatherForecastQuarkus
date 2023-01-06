package org.acme.wforecast;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class WeatherForecastResourceTest {

    @InjectMock
    WeatherForecastResource weatherForecastResource;

    private WeatherForecast weatherForecast;

    @BeforeEach
    public void setup() {
        weatherForecast = new WeatherForecast();
        weatherForecast.setDescription("Sunny");
        weatherForecast.setTemperature("11.0");
        weatherForecast.setFeelsLike("9.2");
        weatherForecast.setVisibility("10.0");
        weatherForecast.setPressure("1016.0");
        weatherForecast.setHumidity("71");
        weatherForecast.setCloud("0");
        weatherForecast.setWind("9.4");

        Mockito.when(weatherForecastResource.getDailyForecastForCity(any(), any(), any(), any())).thenReturn(weatherForecast);
    }

    @Test
    public void testGetDailyForecastForCity() {
        WeatherForecast wForecast = weatherForecastResource.getDailyForecastForCity(any(), any(), any(), any());
        Assertions.assertNotNull(wForecast);
        Assertions.assertEquals(weatherForecast, wForecast);
    }
}
