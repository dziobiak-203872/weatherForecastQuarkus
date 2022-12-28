package org.acme.wprovider;

public class WeatherProvider {

    public String providerId;
    public String url;
    public String apiKey;

    @Override
    public String toString() {
        return "WeatherProvider [providerId=" + providerId + ", url=" + url + "]";
    }
}
