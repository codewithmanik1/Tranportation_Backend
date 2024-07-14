package com.om.operations.dashboard.controller;


import com.om.operations.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/getCurrentVehicleStatsForDriver/{employeeId}")
    public ResponseEntity<?> getCurrentVehicleStatsForDriver(@PathVariable Long employeeId){
        return dashboardService.getCurrentVehicleStatsForDriver(employeeId);
    }
}
