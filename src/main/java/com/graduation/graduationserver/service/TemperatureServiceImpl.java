package com.graduation.graduationserver.service;

import com.graduation.graduationserver.adapter.TemperatureInformationAdapter;
import com.graduation.graduationserver.adapter.WeatherInformationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swaggergen.model.*;

import java.util.ArrayList;
import java.util.Comparator;
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
    private TemperatureInformationAdapter temperatureInformationAdapter;

    @Autowired
    private Map<String, List<Object>> dataList;

    @Autowired
    private Map<String, List<DataPropertiesModel>> linkConfigurations;

    @Override
    public TemperatureResponse getTemperatureValues() {

        TemperatureResponse temperatureResponse = new TemperatureResponse();

        updateDataWithWeatherInformation();

        AverageTemperatureInformationResponse avgTempInfoResponse =
                temperatureInformationAdapter.getAvgTemperatureInformation();

        List<AverageTemperatureInformationResponseAvgTempData> responseData = avgTempInfoResponse.getAvgTempData();
        responseData.sort(new AverageTempComparator());

        List<Object> tempData = new ArrayList<>();

        for (AverageTemperatureInformationResponseAvgTempData data : responseData) {
            tempData.add(data.getAvgTemp().floatValue());
        }

        dataList.remove("historicalTemperatureData");
        dataList.put("historicalTemperatureData", tempData);

        List<DataPropertiesModel> temperatureList = linkConfigurations.get("/temperature/");
        DataPropertiesModel setTemperature = temperatureList.get(temperatureList.size()-1);
        setTemperature.setText("Indoor temperature is set to " + dataList.get("liveTemperatureData").get(0).toString() +
                " Celsius.");
        linkConfigurations.remove("/temperature/");
        temperatureList.remove(temperatureList.size()-1);
        temperatureList.add(setTemperature);
        linkConfigurations.put("/temperature/", temperatureList);

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
                dataModel.setText(propertiesModel.getText());

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

        if (dataList.containsKey("liveWeatherInfoText")) {
            dataList.get("liveWeatherInfoText").clear();
            dataList.get("liveWeatherInfoText").add(text);
        } else {
            List<Object> dummyList = new ArrayList<>();
            dummyList.add(text);
            dataList.put("liveWeatherInfoText", dummyList);
        }

        if (dataList.containsKey("liveWeatherStatusImage")) {
            dataList.get("liveWeatherStatusImage").clear();
            dataList.get("liveWeatherStatusImage").add(iconLink);
        } else {
            List<Object> dummyList = new ArrayList<>();
            dummyList.add(iconLink);
            dataList.put("liveWeatherStatusImage", dummyList);
        }

    }
    class AverageTempComparator implements Comparator<AverageTemperatureInformationResponseAvgTempData> {
        @Override
        public int compare(AverageTemperatureInformationResponseAvgTempData a,
                           AverageTemperatureInformationResponseAvgTempData b) {
            return a.getAvgTime() < b.getAvgTime() ? -1 : a.getAvgTime() == b.getAvgTime() ? 0 : 1;
        }
    }

}
