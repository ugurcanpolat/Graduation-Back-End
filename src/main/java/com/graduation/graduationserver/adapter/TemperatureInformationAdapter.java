package com.graduation.graduationserver.adapter;

import swaggergen.model.AverageTemperatureInformationResponse;
import swaggergen.model.TemperatureInformationResponse;

public interface TemperatureInformationAdapter {

    TemperatureInformationResponse getTemperatureInformation();
    AverageTemperatureInformationResponse getAvgTemperatureInformation();

}
