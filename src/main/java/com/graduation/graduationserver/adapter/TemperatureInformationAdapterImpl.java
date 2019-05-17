package com.graduation.graduationserver.adapter;

import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import swaggergen.model.AverageTemperatureInformationResponse;
import swaggergen.model.TemperatureInformationResponse;

@Service
public class TemperatureInformationAdapterImpl implements TemperatureInformationAdapter {

    private static final String URL = "http://meralkorkmaz95.pythonanywhere.com/";

    @Override
    public TemperatureInformationResponse getTemperatureInformation() {
        String temperatureURL = URL + "getTemp";

        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate.getForObject(temperatureURL,
                TemperatureInformationResponse.class);
    }

    @Override
    public AverageTemperatureInformationResponse getAvgTemperatureInformation() {
        String avgURL = URL + "getAvgTemp";

        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate.getForObject(avgURL,
                AverageTemperatureInformationResponse.class);
    }

}
