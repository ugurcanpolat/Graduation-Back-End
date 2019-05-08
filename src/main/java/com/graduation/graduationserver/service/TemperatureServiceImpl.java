package com.graduation.graduationserver.service;

import com.graduation.graduationserver.adapter.WeatherInformationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swaggergen.model.DataModel;
import swaggergen.model.DataPropertiesModel;
import swaggergen.model.TemperatureResponse;
import swaggergen.model.WeatherInformationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private static final String apiKey = "fWHTiau9fI1vaGYsRCiVtl7NVU5WZMHS";
    //private static final String apiKey = "Lbxsrk4ybXI4q0j5wP9CSRAUn1l0VLxc";
    private static final String cityId = "318218";

    @Autowired
    private WeatherInformationAdapter weatherInformationAdapter;

    @Autowired
    private Map<String, List<Object>> dataList;

    @Autowired
    private Map<String, List<DataPropertiesModel>> linkConfigurations;

    @Override
    public TemperatureResponse getTemperatureValues() {

        TemperatureResponse temperatureResponse = new TemperatureResponse();

        updateDataWithWeatherInformation();

        List<DataPropertiesModel> properties = linkConfigurations.get("/temperature/");
        List<DataModel> dataModelList = new ArrayList<>();

        for (DataPropertiesModel propertiesModel : properties) {
            if (dataList.containsKey(propertiesModel.getName())) {
                DataModel dataModel = new DataModel();
                dataModel.setName(propertiesModel.getName());
                dataModel.setVisual(propertiesModel.getVisual());
                dataModel.setScreenLocation(propertiesModel.getScreenLocation());
                dataModel.setModifiable(propertiesModel.isModifiable());

                List<Object> data = dataList.get(propertiesModel.getName());

                dataModel.setValues(data);

                if (data.get(0) instanceof Integer) {
                    dataModel.setDataType("integer");
                } else if (data.get(0) instanceof String) {
                    dataModel.setDataType("string");
                } else if (data.get(0) instanceof Number){
                    dataModel.setDataType("float");
                } else {
                    dataModel.setDataType("string");
                }

                dataModelList.add(dataModel);
            }
        }

        temperatureResponse.setSuccess(true);
        temperatureResponse.setData(dataModelList);

        return temperatureResponse;

    }

    private void updateDataWithWeatherInformation() {

        WeatherInformationResponse weather = weatherInformationAdapter
                                                .getWeatherInformation(apiKey, cityId);

        String text = "The weather outside is " + weather.getWeatherText().toLowerCase() + " and "+
                weather.getTemperature().getMetric().getValue().toString() + " degree Celsius.";

        String iconLink = "https://developer.accuweather.com/sites/default/files/";

        if (weather.getWeatherIcon() < 10) {
            iconLink += "0" + weather.getWeatherIcon().toString() + "-s.png";
        } else {
            iconLink += weather.getWeatherIcon().toString() + "-s.png";
        }

        if (dataList.containsKey("text")) {
            dataList.get("text").clear();
            dataList.get("text").add(text);
        } else {
            List<Object> dummyList = new ArrayList<>();
            dummyList.add(text);
            dataList.put("text", dummyList);
        }

        if (dataList.containsKey("image")) {
            dataList.get("image").clear();
            dataList.get("image").add(iconLink);
        } else {
            List<Object> dummyList = new ArrayList<>();
            dummyList.add(iconLink);
            dataList.put("image", dummyList);
        }

    }

}
