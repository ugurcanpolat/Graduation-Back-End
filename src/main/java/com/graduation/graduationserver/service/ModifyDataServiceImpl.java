package com.graduation.graduationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swaggergen.model.ModifyDataResponse;

import java.util.List;
import java.util.Map;

@Service
public class ModifyDataServiceImpl implements ModifyDataService {

    @Autowired
    private Map<String, List<Object>> dataList;

    @Override
    public ModifyDataResponse modifyData(String name, String operation, Object value, int index) {

        if (!dataList.containsKey(name)) {
            return CreateResponse(false, "Data not found.");
        }

        if (operation == null) {
            return CreateResponse(false, "Operation is not provided.");
        }

        if (operation.compareTo("delete") != 0 && dataList.get(name).size() > 0) {
            if (value == null) {
                return CreateResponse(false, "Value is not provided.");
            }

            if (!dataList.get(name).get(0).getClass().isAssignableFrom(value.getClass())) {
                return CreateResponse(false, "Data type of provided value is not compatible.");
            }
        }

        if (operation.compareTo("add") != 0 && dataList.get(name).size() < index+1) {
            return CreateResponse(false, "Index exceeds the size.");
        }

        switch (operation) {
            case "add":
                return addToDataList(name, value);
            case "delete":
                return deleteFromDataList(name, index);
            case "modify":
                return modifyDataFromDataList(name, index, value);
            default:
                return CreateResponse(false, "Invalid operation.");
        }

    }

    private ModifyDataResponse CreateResponse(boolean success, String errorMsg) {
        ModifyDataResponse modifyDataResponse = new ModifyDataResponse();

        modifyDataResponse.setSuccess(success);
        modifyDataResponse.setErrorMsg(errorMsg);
        return modifyDataResponse;
    }

    private ModifyDataResponse addToDataList(String name, Object value) {
        System.out.println("Add operation has performed.");
        dataList.get(name).add(value);

        return CreateResponse(true, null);
    }

    private ModifyDataResponse deleteFromDataList(String name, int index) {
        System.out.println("Delete operation has performed.");
        dataList.get(name).remove(index);

        return CreateResponse(true, null);
    }

    private ModifyDataResponse modifyDataFromDataList(String name, int index, Object value) {
        System.out.println("Modify operation has performed.");
        dataList.get(name).set(index, value);

        return CreateResponse(true, null);
    }

}
