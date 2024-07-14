package com.om.operations.operations.admin.service.impl;
import com.om.operations.common.ApiResponse;
import com.om.operations.operations.admin.dto.request.AssignVehicleReqDto;
import com.om.operations.operations.admin.entity.AssignVehicle;
import com.om.operations.operations.admin.repository.AssignVehicleRepo;
import com.om.operations.operations.admin.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private AssignVehicleRepo assignVehicleRepo;

    @Override
    public ResponseEntity<?> assignVehicleToDriver(AssignVehicleReqDto assignVehicleReqDto) {
        var response = new ApiResponse<>();
        AssignVehicle assignVehicle = new AssignVehicle();
        assignVehicle.setEmployeeId(assignVehicleReqDto.getEmployeeId());
        assignVehicle.setVehicleId(assignVehicleReqDto.getVehicleId());
        assignVehicle.setRouteId(assignVehicleReqDto.getRouteId());
        assignVehicle.setTotalMaterial(assignVehicleReqDto.getTotalMaterial());
        assignVehicle.setAssignById(assignVehicleReqDto.getAssignById());
        assignVehicle.setReturnInVehicle(assignVehicleReqDto.getTotalMaterial());
        assignVehicleRepo.save(assignVehicle);
        response.responseMethod(HttpStatus.OK.value(), "Vehicle assign successfully", null, null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> employeeDropDownForAssignVehicle(String searchString) {
        var response = new ApiResponse<>();
        List<Map<String, Object>> getData = assignVehicleRepo.employeeDropDownForAssignVehicle(searchString);

        if(!getData.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", getData, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Data not found", getData, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> vehicleDropDownForAssignVehicle(String searchString) {
        var response = new ApiResponse<>();
        List<Map<String, Object>> getData = assignVehicleRepo.vehicleDropDownForAssignVehicle(searchString);

        if(!getData.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", getData, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Data not found", getData, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> routeDropDownForAssignVehicle(String searchString) {
        var response = new ApiResponse<>();
        List<Map<String, Object>> getData = assignVehicleRepo.routeDropDownForAssignVehicle(searchString);

        if(!getData.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", getData, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Data not found", getData, null);

        return ResponseEntity.ok(response);
    }
}
