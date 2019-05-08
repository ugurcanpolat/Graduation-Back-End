package com.graduation.graduationserver.controller;

import com.graduation.graduationserver.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swaggergen.controller.TemperatureApi;
import swaggergen.model.TemperatureRequest;
import swaggergen.model.TemperatureResponse;

import javax.validation.Valid;

@RestController
public class TemperatureController implements TemperatureApi {

    @Autowired
    private TemperatureService temperatureService;

    @Override
    public ResponseEntity<TemperatureResponse> temperatureDataGet(TemperatureRequest request) {
        TemperatureResponse temperatureResponse = temperatureService.getTemperatureValues();

        System.out.println("/temperature/ has been requested.");

        return new ResponseEntity<>(temperatureResponse, HttpStatus.OK);
    }

}
