package org.acme.wforecast.citymapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {

    public String name;

    @JsonProperty("lat")
    public String latitude;

    @JsonProperty("lon")
    public String longitude;

    @Override
    public String toString() {
        return "City [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }
}
