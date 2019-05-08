package com.graduation.graduationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swaggergen.model.DataPropertiesModel;
import swaggergen.model.ModifyConfigurationResponse;

import java.util.List;
import java.util.Map;

@Service
public class ModifyConfigurationServiceImpl implements ModifyConfigurationService {

    @Autowired
    private Map<String, List<DataPropertiesModel>> linkConfigurations;

    @Override
    public ModifyConfigurationResponse modifyConfiguration(String link, List<DataPropertiesModel> configuration) {
        ModifyConfigurationResponse response = new ModifyConfigurationResponse();

        linkConfigurations.put(link, configuration);

        response.setSuccess(true);

        return response;
    }

}
