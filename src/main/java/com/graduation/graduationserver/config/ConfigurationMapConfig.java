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
        temperature.setName("historicalTemperatureData");
        temperature.setScreenLocation(1);
        temperature.setVisual("bar");
        temperature.setModifiable(false);

        DataPropertiesModelLabels labels = new DataPropertiesModelLabels();
        labels.setHorizontal("Time (seconds)");
        labels.setVertical("Temperature (Celsius)");
        temperature.setLabels(labels);
        propertiesModelList.add(temperature);

        DataPropertiesModel text = new DataPropertiesModel();
        text.setName("liveWeatherInfoText");
        text.setScreenLocation(3);
        text.setVisual("text");
        text.setModifiable(false);
        propertiesModelList.add(text);

        DataPropertiesModel image = new DataPropertiesModel();
        image.setName("liveWeatherStatusImage");
        image.setScreenLocation(2);
        image.setVisual("image");
        image.setModifiable(false);
        propertiesModelList.add(image);

        DataPropertiesModel thermostat = new DataPropertiesModel();
        thermostat.setName("liveTemperatureData");
        thermostat.setScreenLocation(4);
        thermostat.setVisual("data-text");
        thermostat.setModifiable(true);
        thermostat.setText("-");
        propertiesModelList.add(thermostat);

        configuration.put("/temperature/", propertiesModelList);

        return configuration;

    }

}
