package org.acme.wforecast;

import java.util.ArrayList;
import java.util.Arrays;

import org.acme.wforecast.citymapping.City;
import org.acme.wforecast.citymapping.CityService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.Mock;

@Mock
@RestClient
public class MockCityService implements CityService {

    @Override
    public ArrayList<City> getCityLocationData(String cityData) {
        City city = new City();
        city.setCountry("GB");
        city.setName("London");
        city.setState("England");
        city.setLatitude("51.5073219");
        city.setLongitude("-0.1276474");
        return new ArrayList<City>(Arrays.asList(city));
    }
}
