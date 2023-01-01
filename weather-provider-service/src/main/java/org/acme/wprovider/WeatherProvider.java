package org.acme.wprovider;

import java.util.ArrayList;

public class WeatherProvider {

    public String providerId;
    public String url;
    public String apiKey;
    public ArrayList<String> params;

    @Override
    public String toString() {
        return "WeatherProvider [providerId=" + providerId + ", url=" + url + ", params="
                + params + ", delimiter=" + "]";
    }
}
