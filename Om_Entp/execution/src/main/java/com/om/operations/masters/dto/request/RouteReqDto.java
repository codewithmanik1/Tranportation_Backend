package com.om.operations.masters.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteReqDto {


    private Long id;

    private String route;

    private Boolean isActive = true;

    private Long createdBy;

    private Long updatedBy;
}
