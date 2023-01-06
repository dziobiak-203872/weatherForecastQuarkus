package org.acme.wforecast.citymapping;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@EqualsAndHashCode
public class City {

    public String name;
    @JsonProperty("lat")
    public String latitude;
    @JsonProperty("lon")
    public String longitude;
    public String country;
    public String state;
}
