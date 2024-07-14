package com.om.operations.dashboard.service.impl;

import com.om.operations.common.ApiResponse;
import com.om.operations.dashboard.dto.response.CurrentVehicleStatsForDriver;
import com.om.operations.dashboard.service.DashboardService;
import com.om.operations.operations.admin.repository.AssignVehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private AssignVehicleRepo assignVehicleRepo;


    @Override
    public ResponseEntity<?> getCurrentVehicleStatsForDriver(Long employeeId) {
        var response = new ApiResponse<>();
        List<CurrentVehicleStatsForDriver> getData = assignVehicleRepo.getCurrentVehicleStatsForDriver(employeeId);
        if(!getData.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", getData, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Data not found", getData, null);

        return ResponseEntity.ok(response);
    }
}
