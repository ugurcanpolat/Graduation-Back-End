package com.graduation.graduationserver.config;

import com.graduation.graduationserver.adapter.TemperatureInformationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swaggergen.model.TemperatureInformationResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataListConfig {

    @Autowired
    private TemperatureInformationAdapter temperatureInformationAdapter;

    @Bean
    public Map<String, List<Object>> data() {
        Map<String, List<Object>> dataList = new HashMap<>();

        TemperatureInformationResponse tempInfoResponse = temperatureInformationAdapter.getTemperatureInformation();

        List<Object> tempInfoTextDummyValues = new ArrayList<>();
        tempInfoTextDummyValues.add("Thermostat is set to " + tempInfoResponse.getTemperature().toString() + " Celsius.");
        dataList.put("thermostatText", tempInfoTextDummyValues);

        List<Object> tempInfoDummyValues = new ArrayList<>();
        tempInfoDummyValues.add(tempInfoResponse.getTemperature());
        dataList.put("thermostat", tempInfoDummyValues);

        List<Object> temperatureDummyValues = new ArrayList<>();
        temperatureDummyValues.add(21.2f);
        temperatureDummyValues.add(21.3f);
        temperatureDummyValues.add(22.9f);
        temperatureDummyValues.add(21.1f);

        dataList.put("temperature", temperatureDummyValues);

        List<Object> textDummyValues = new ArrayList<>();

        dataList.put("text", textDummyValues);

        List<Object> imageDummyValues = new ArrayList<>();

        dataList.put("image", imageDummyValues);

        return dataList;
    }

}
