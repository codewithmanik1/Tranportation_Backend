package com.om.operations.masters.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleReqDto {

    private Long id;

    private String role;

    private Long createdBy;

    private Boolean isActive;

    private Long updatedBy;

}
