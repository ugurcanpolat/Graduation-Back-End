package com.graduation.graduationserver.controller;

import com.graduation.graduationserver.service.DebugTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import swaggergen.controller.DebugApi;
import swaggergen.model.TemperatureRequest;
import swaggergen.model.TemperatureResponse;

@RestController
public class DebugTemperatureController implements DebugApi {

    @Autowired
    private DebugTemperatureService temperatureService;

    @Override
    public ResponseEntity<TemperatureResponse> temperatureDataGet(TemperatureRequest request) {
        TemperatureResponse temperatureResponse = temperatureService.getTemperatureValues();

        System.out.println("/debug/temperature/ has been requested.");

        return new ResponseEntity<>(temperatureResponse, HttpStatus.OK);
    }

}
