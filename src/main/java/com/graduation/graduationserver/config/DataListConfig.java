package com.graduation.graduationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataListConfig {

    @Bean
    public Map<String, List<Object>> data() {
        Map<String, List<Object>> dataList = new HashMap<>();

        List<Object> temperatureDummyValues = new ArrayList<>();
        temperatureDummyValues.add(24.8);
        temperatureDummyValues.add(23.2);
        temperatureDummyValues.add(24.9);
        temperatureDummyValues.add(26.4);
        temperatureDummyValues.add(12.3);
        temperatureDummyValues.add(39.2);
        temperatureDummyValues.add(100.2);

        dataList.put("temperature", temperatureDummyValues);

        List<Object> textDummyValues = new ArrayList<>();

        dataList.put("text", textDummyValues);

        List<Object> imageDummyValues = new ArrayList<>();

        dataList.put("image", imageDummyValues);

        return dataList;
    }

}
