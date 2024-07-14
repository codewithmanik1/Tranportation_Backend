package com.om.operations.operations.admin.controller;


import com.om.operations.operations.admin.dto.request.AssignVehicleReqDto;
import com.om.operations.operations.admin.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminOperations")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AdminController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/assignVehicle")
    public ResponseEntity<?> assignVehicle(@RequestBody AssignVehicleReqDto assignVehicleReqDto){
        return operationService.assignVehicleToDriver(assignVehicleReqDto);
    }

    @GetMapping("/employeeDropDownForAssignVehicle")
    public ResponseEntity<?> employeeDropDownForAssignVehicle(@RequestParam(required = false) String searchString){
        return operationService.employeeDropDownForAssignVehicle(searchString);
    }

    @GetMapping("/vehicleDropDownForAssignVehicle")
    public ResponseEntity<?> vehicleDropDownForAssignVehicle(@RequestParam(required = false) String searchString){
        return operationService.vehicleDropDownForAssignVehicle(searchString);
    }

    @GetMapping("/routeDropDownForAssignVehicle")
    public ResponseEntity<?> routeDropDownForAssignVehicle(@RequestParam(required = false) String searchString){
        return operationService.routeDropDownForAssignVehicle(searchString);
    }
}
