package org.acme.wforecast;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.acme.wforecast.citymapping.City;
import org.acme.wforecast.citymapping.CityService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;

@QuarkusTest
public class CityServiceTest {

    @InjectMock
    @RestClient
    CityService cityService;

    private City city;

    @BeforeEach
    public void setup() {
        city = new City();
        city.setCountry("GB");
        city.setName("London");
        city.setState("England");
        city.setLatitude("51.5073219");
        city.setLongitude("-0.1276474");

        Mockito.when(cityService.getCityLocationData(anyString())).thenReturn(new ArrayList<City>(Arrays.asList(city)));
    }

    @Test
    public void testGetCityLocationData() {
        ArrayList<City> actualCities = cityService.getCityLocationData(anyString());
        Assertions.assertNotNull(actualCities);
        Assertions.assertTrue(1 == actualCities.size());
        Assertions.assertEquals(city, actualCities.get(0));
    }
}
