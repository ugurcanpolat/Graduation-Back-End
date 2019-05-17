package com.graduation.graduationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swaggergen.model.DataPropertiesModel;
import swaggergen.model.DataPropertiesModelLabels;

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
        temperature.setScreenLocation(1);
        temperature.setVisual("line");
        temperature.setModifiable(false);

        DataPropertiesModelLabels labels = new DataPropertiesModelLabels();
        labels.setHorizontal("Time (seconds)");
        labels.setVertical("Temperature (Celsius)");
        temperature.setLabels(labels);
        propertiesModelList.add(temperature);

        DataPropertiesModel text = new DataPropertiesModel();
        text.setName("text");
        text.setScreenLocation(3);
        text.setVisual("text");
        text.setModifiable(false);
        propertiesModelList.add(text);

        DataPropertiesModel image = new DataPropertiesModel();
        image.setName("image");
        image.setScreenLocation(2);
        image.setVisual("image");
        image.setModifiable(false);
        propertiesModelList.add(image);

        DataPropertiesModel thermostat = new DataPropertiesModel();
        thermostat.setName("thermostat");
        thermostat.setScreenLocation(0);
        thermostat.setVisual("other");
        thermostat.setModifiable(true);
        propertiesModelList.add(thermostat);

        DataPropertiesModel thermostatText = new DataPropertiesModel();
        thermostatText.setName("thermostatText");
        thermostatText.setScreenLocation(4);
        thermostatText.setVisual("text");
        thermostatText.setModifiable(false);
        propertiesModelList.add(thermostatText);

        configuration.put("/temperature/", propertiesModelList);

        return configuration;

    }

}
