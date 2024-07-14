package com.om.operations.masters.dto.request;

import com.om.operations.masters.entity.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReqDto {

    private Long id;

    private Route routeId;

    private String shopName;

    private Boolean isActive = true;

    private Long createdBy;

    private Long updatedBy;

}
