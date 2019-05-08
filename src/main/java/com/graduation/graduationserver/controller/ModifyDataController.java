package com.graduation.graduationserver.controller;

import com.graduation.graduationserver.service.ModifyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swaggergen.controller.ModifyDataApi;
import swaggergen.model.*;

import javax.validation.Valid;

@RestController
public class ModifyDataController implements ModifyDataApi {

    @Autowired
    private ModifyDataService modifyDataService;

    @Override
    public ResponseEntity<ModifyDataResponse> modifyData(@RequestBody @Valid ModifyDataRequest request) {

        ModifyDataResponse modifyDataResponse = modifyDataService.modifyData(request.getName(),
                request.getOperation(), request.getValue(), request.getIndex());

        System.out.println("/modifyData/ has been requested.");

        return new ResponseEntity<>(modifyDataResponse, HttpStatus.OK);
    }

}
