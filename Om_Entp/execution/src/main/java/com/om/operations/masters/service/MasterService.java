package com.om.operations.masters.service;


import com.om.operations.masters.dto.request.*;
import org.springframework.http.ResponseEntity;

public interface MasterService {

    ResponseEntity<?> saveEmployees(EmployeeReqDto employeeReqDto);

    ResponseEntity<?> saveVehicle(VehicleReqDto vehicleReqDto);

    ResponseEntity<?> saveRoute(RouteReqDto routeReqDto);

    ResponseEntity<?> saveRole(RoleReqDto roleReqDto);

    ResponseEntity<?> saveShop(ShopReqDto shopReqDto);
}
