package com.graduation.graduationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swaggergen.model.DataModel;
import swaggergen.model.DataPropertiesModel;
import swaggergen.model.TemperatureResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class DebugTemperatureServiceImpl implements DebugTemperatureService {
    @Autowired
    private Map<String, List<Object>> dataList;

    @Autowired
    private Map<String, List<DataPropertiesModel>> linkConfigurations;

    @Override
    public TemperatureResponse getTemperatureValues() {

        TemperatureResponse temperatureResponse = new TemperatureResponse();

        updateDataWithWeatherInformation();

        List<Object> tempInfoTextDummyValues = new ArrayList<>();
        tempInfoTextDummyValues.add("Thermostat is set to " + dataList.get("thermostat").get(0).toString() +
                " Celsius.");
        dataList.remove("thermostatText");
        dataList.put("thermostatText", tempInfoTextDummyValues);

        Random rd = new Random();

        Double thermostat = (Double) dataList.get("thermostat").get(0);
        Float lastTemperature = (Float) dataList.get("temperature").get(dataList.get("temperature").size()-1);

        if (thermostat.floatValue() - lastTemperature > 0.4) {
            float dummy = lastTemperature + (float) (rd.nextInt(3) + 1) / 10;
            System.out.println("Option 1: " + dummy);
            dataList.get("temperature").add(dummy);
        } else if (lastTemperature - thermostat.floatValue() > 0.4) {
            float dummy = lastTemperature - (float) (rd.nextInt(3) + 1) / 10;
            System.out.println("Option 2: " + dummy);
            dataList.get("temperature").add(dummy);
        } else {
            float dummy = lastTemperature + (float) (rd.nextInt(4) - 2) / 10;
            System.out.println("Option 3: " + dummy);
            dataList.get("temperature").add(dummy);
        }

        List<DataPropertiesModel> properties = linkConfigurations.get("/temperature/");
        List<DataModel> dataModelList = new ArrayList<>();

        for (DataPropertiesModel propertiesModel : properties) {
            if (dataList.containsKey(propertiesModel.getName())) {
                DataModel dataModel = new DataModel();
                dataModel.setName(propertiesModel.getName());
                dataModel.setVisual(propertiesModel.getVisual());
                dataModel.setScreenLocation(propertiesModel.getScreenLocation());
                dataModel.setModifiable(propertiesModel.isModifiable());
                dataModel.setLabels(propertiesModel.getLabels());

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

        String text = "The weather outside is " + "Clear" + " and "+
               "21.2" + " degree Celsius.";

        String iconLink = "https://developer.accuweather.com/sites/default/files/01-s.png";

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
