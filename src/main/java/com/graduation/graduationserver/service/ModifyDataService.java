package com.graduation.graduationserver.service;

import swaggergen.model.ModifyDataResponse;

public interface ModifyDataService {

    ModifyDataResponse modifyData(String name, String operation, Object value, int index);

}
