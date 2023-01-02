package org.acme.wforecast.citymapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {

    public String name;

    @JsonProperty("lat")
    public String latitude;

    @JsonProperty("lon")
    public String longitude;

    public String country;

    public String state;

    @Override
    public String toString() {
        return "City [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", country=" + country
                + ", state=" + state + "]";
    }
}
