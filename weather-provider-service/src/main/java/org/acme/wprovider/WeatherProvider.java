package org.acme.wprovider;

public class WeatherProvider {

    public String name;
    public String url;
    public String apiKey;

    @Override
    public String toString() {
        return "WeatherProvider [name=" + name + ", url=" + url + "]";
    }
}
