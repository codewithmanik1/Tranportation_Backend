package com.om.operations.operations.admin.service;

import com.om.operations.operations.admin.dto.request.AssignVehicleReqDto;
import org.springframework.http.ResponseEntity;

public interface OperationService {

    ResponseEntity<?> assignVehicleToDriver(AssignVehicleReqDto assignVehicleReqDto);

    ResponseEntity<?> employeeDropDownForAssignVehicle(String searchString );

    ResponseEntity<?> vehicleDropDownForAssignVehicle(String searchString);

    ResponseEntity<?> routeDropDownForAssignVehicle(String searchString);
}
