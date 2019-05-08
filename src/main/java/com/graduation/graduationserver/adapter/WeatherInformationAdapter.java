package com.graduation.graduationserver.adapter;

import swaggergen.model.WeatherInformationResponse;

public interface WeatherInformationAdapter {

    WeatherInformationResponse getWeatherInformation(final String apiKey, final String cityId);

}
