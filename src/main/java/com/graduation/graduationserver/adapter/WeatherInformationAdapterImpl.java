package com.graduation.graduationserver.adapter;

import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import swaggergen.model.WeatherInformationResponse;

import java.util.List;

@Service
public class WeatherInformationAdapterImpl implements WeatherInformationAdapter {

    private static final String URL = "http://dataservice.accuweather.com/currentconditions/v1/";

    @Override
    public WeatherInformationResponse getWeatherInformation(final String apiKey, final String cityId) {
        String getURL = URL + cityId + "?apikey=" + apiKey;

        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        WeatherInformationResponse[] response = restTemplate.getForObject(getURL, WeatherInformationResponse[].class);

        return response[0];
    }

}

