package com.om.operations.masters.controller;

import com.om.operations.masters.dto.request.*;
import com.om.operations.masters.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masters")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MasterController {

    @Autowired
    private MasterService masterService;

    @PostMapping("/saveEmployees")
    public ResponseEntity<?> saveEmployees(@RequestBody EmployeeReqDto employeeReqDto){
        return masterService.saveEmployees(employeeReqDto);
    }

    @PostMapping("/saveVehicle")
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleReqDto vehicleReqDto){
        return masterService.saveVehicle(vehicleReqDto);
    }

    @PostMapping("/saveRoute")
    public ResponseEntity<?> saveRoute(@RequestBody RouteReqDto routeReqDto){
        return masterService.saveRoute(routeReqDto);
    }

    @PostMapping("/saveRole")
    public ResponseEntity<?> saveEmployees(@RequestBody RoleReqDto roleReqDto){
        return masterService.saveRole(roleReqDto);
    }

    @PostMapping("/saveShop")
    public ResponseEntity<?> saveShop(@RequestBody ShopReqDto shopReqDto){
        return masterService.saveShop(shopReqDto);
    }
}
