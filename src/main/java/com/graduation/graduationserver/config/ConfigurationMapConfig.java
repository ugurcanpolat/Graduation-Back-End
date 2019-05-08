package com.graduation.graduationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swaggergen.model.DataPropertiesModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ConfigurationMapConfig {

    @Bean
    public Map<String, List<DataPropertiesModel>> configurationMap() {

        Map<String, List<DataPropertiesModel>> configuration = new HashMap<>();

        List<DataPropertiesModel> propertiesModelList = new ArrayList<>();

        DataPropertiesModel temperature = new DataPropertiesModel();
        temperature.setName("temperature");
        temperature.setScreenLocation(3);
        temperature.setVisual("pie");
        temperature.setModifiable(true);
        propertiesModelList.add(temperature);

        DataPropertiesModel text = new DataPropertiesModel();
        text.setName("text");
        text.setScreenLocation(1);
        text.setVisual("text");
        text.setModifiable(true);
        propertiesModelList.add(text);

        DataPropertiesModel image = new DataPropertiesModel();
        image.setName("image");
        image.setScreenLocation(2);
        image.setVisual("image");
        image.setModifiable(false);
        propertiesModelList.add(image);

        configuration.put("/temperature/", propertiesModelList);

        return configuration;

    }

}
