package com.om.operations.dashboard.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<?> getCurrentVehicleStatsForDriver(Long employeeId);

}
