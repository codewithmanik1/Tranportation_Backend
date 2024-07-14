package com.om.operations.operations.admin.dto.request;

import com.om.operations.masters.entity.Employee;
import com.om.operations.masters.entity.Route;
import com.om.operations.masters.entity.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignVehicleReqDto {

    private Long id;

    private Employee employeeId;

    private Vehicle vehicleId;

    private Route routeId;

    private Double totalMaterial;

    private Employee assignById;
}
