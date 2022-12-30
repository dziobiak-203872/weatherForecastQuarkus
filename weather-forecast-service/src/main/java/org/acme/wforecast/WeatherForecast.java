package org.acme.wforecast;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherForecast {

    public String description;
    public String temperature;
    public String feelsLike;
    public String visibility;
    public String pressure;
    public String humidity;
    public String cloud;
    public String wind;

    @SuppressWarnings("unchecked")
    @JsonProperty("current")
    public void unpackNestedVisibility(Map<String, Object> current) {
        this.visibility = current.get("vis_km").toString();
        this.feelsLike = current.get("feelslike_c").toString();
        this.pressure = current.get("pressure_mb").toString();
        this.humidity = current.get("humidity").toString();
        this.temperature = current.get("temp_c").toString();
        this.cloud = current.get("cloud").toString();
        this.wind = current.get("wind_mph").toString();
        Map<String, Object> condition = (Map<String, Object>) current.get("condition");
        this.description = condition.get("text").toString();
    }

    @JsonProperty("main")
    public void unpackNestedFeelsLike(Map<String, Object> main) {
        this.feelsLike = main.get("feels_like").toString();
        this.pressure = main.get("pressure").toString();
        this.humidity = main.get("humidity").toString();
        this.temperature = main.get("temp").toString();
    }

    @JsonProperty("clouds")
    public void unpackNestedClouds(Map<String, Object> clouds) {
        this.cloud = clouds.get("all").toString();
    }

    @JsonProperty("wind")
    public void unpackNestedWind(Map<String, Object> wind) {
        this.wind = wind.get("speed").toString();
    }

    @JsonProperty("weather")
    public void unpackNestedDescription(List<Map<String, Object>> weather) {
        Map<String, Object> weatherList = (Map<String, Object>) weather.get(0);
        this.description = weatherList.get("description").toString();
    }
}
