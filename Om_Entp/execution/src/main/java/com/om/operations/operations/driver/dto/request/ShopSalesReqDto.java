package com.om.operations.operations.driver.dto.request;

import com.om.operations.masters.entity.Shops;
import com.om.operations.operations.admin.entity.AssignVehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopSalesReqDto {

    private AssignVehicle assignId;

    private Shops shopId;

    private Boolean isCash;

    private Double cashAmount;

    private Boolean isOnline;

    private Double onlineAmount;

    private String onlinePhoto;

    private Boolean isCheck;

    private Double checkAmount;

    private String checkPhoto;

    private Boolean isBalance;

    private Double balanceAmount;

    private String balancePhoto;

    private Boolean isDiscount;

    private Double discountAmount;

}
