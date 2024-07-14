package com.om.operations.masters.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleReqDto {

    private Long id;

    private String vehicleNo;

    private Boolean isActive = true;

    private Long createdBy;

    private Long updatedBy;
}
