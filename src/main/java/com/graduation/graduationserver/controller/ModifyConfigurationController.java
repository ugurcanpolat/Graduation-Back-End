package com.graduation.graduationserver.controller;

import com.graduation.graduationserver.service.ModifyConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swaggergen.controller.ModifyConfigurationApi;
import swaggergen.model.ModifyConfigurationRequest;
import swaggergen.model.ModifyConfigurationResponse;

import javax.validation.Valid;

@RestController
public class ModifyConfigurationController implements ModifyConfigurationApi {

    @Autowired
    private ModifyConfigurationService modifyConfigurationService;

    @Override
    public ResponseEntity<ModifyConfigurationResponse> modifyConfiguration(@RequestBody @Valid ModifyConfigurationRequest request) {
        ModifyConfigurationResponse response = modifyConfigurationService.modifyConfiguration(
                request.getLink(), request.getConfiguration()
        );

        System.out.println("/modifyConfiguration/ has been requested for "+ request.getLink() + ".");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}