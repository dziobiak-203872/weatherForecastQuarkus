package org.acme.wforecast;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.acme.wforecast.citymapping.City;
import org.acme.wforecast.citymapping.CityService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import javax.inject.Inject;

@QuarkusTest
public class CityServiceTest {

    @Inject
    @RestClient
    CityService cityService;

    @Test
    public void testGetStatisticsForCityAuthorization() {
        City expectedCity = new City();
        expectedCity.setCountry("GB");
        expectedCity.setName("London");
        expectedCity.setState("England");
        expectedCity.setLatitude("51.5073219");
        expectedCity.setLongitude("-0.1276474");

        ArrayList<City> actualCities = cityService.getCityLocationData("DummyCityData");
        Assertions.assertNotNull(actualCities);
        Assertions.assertEquals(expectedCity, actualCities.get(0));
    }
}
