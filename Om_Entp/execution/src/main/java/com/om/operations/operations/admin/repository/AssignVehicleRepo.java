package com.om.operations.operations.admin.repository;

import com.om.operations.dashboard.dto.response.CurrentVehicleStatsForDriver;
import com.om.operations.operations.admin.entity.AssignVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AssignVehicleRepo extends JpaRepository<AssignVehicle, Long> {

    //drop-down for assign vehicle screen
    @Query(value = "select * from fn_get_employee_drop_down(?1)", nativeQuery = true)
    List<Map<String, Object>> employeeDropDownForAssignVehicle(String searchString);

    @Query(value = "select * from fn_get_vehicle_drop_down(?1)", nativeQuery = true)
    List<Map<String, Object>> vehicleDropDownForAssignVehicle(String searchString);

    @Query(value = "select * from fn_get_route_drop_down(?1)", nativeQuery = true)
    List<Map<String, Object>> routeDropDownForAssignVehicle(String searchString);

    //dashboard
    @Query(value = "select * from fn_getCurrentVehicleStatsForDriver(?1)", nativeQuery = true)
    List<CurrentVehicleStatsForDriver> getCurrentVehicleStatsForDriver(Long employeeId);

}
