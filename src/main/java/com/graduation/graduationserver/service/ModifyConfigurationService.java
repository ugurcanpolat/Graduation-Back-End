package com.graduation.graduationserver.service;

import swaggergen.model.DataPropertiesModel;
import swaggergen.model.ModifyConfigurationResponse;

import java.util.List;

public interface ModifyConfigurationService {

    ModifyConfigurationResponse modifyConfiguration(String link, List<DataPropertiesModel> configuration);

}
