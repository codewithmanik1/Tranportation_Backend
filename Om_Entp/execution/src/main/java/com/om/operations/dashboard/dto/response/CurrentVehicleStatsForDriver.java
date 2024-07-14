package com.om.operations.dashboard.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Employee Id",
        "Employee Name",
        "Route Id",
        "Route Name",
        "Vehicle Id",
        "Vehicle No",
        "Assign Id",
        "Total Material",
        "Total Sale",
        "Current In Vehicle",
        "Total Expense",
        "Total Discount"
})
public interface CurrentVehicleStatsForDriver {

    @JsonProperty("Employee Id")
    Long getEmpId();

    @JsonProperty("Employee Name")
    String getEmpName();

    @JsonProperty("Route Id")
    Long getRouteId();

    @JsonProperty("Route Name")
    String getRouteName();

    @JsonProperty("Vehicle Id")
    Long getVehicleId();

    @JsonProperty("Vehicle No")
    String getVehicleNo();

    @JsonProperty("Assign Id")
    Long getAssignId();

    @JsonProperty("Total Material")
    Double getTotalMaterial();

    @JsonProperty("Total Sale")
    Double getTotalSale();

    @JsonProperty("Current In Vehicle")
    Double getCurrentInVehicle();

    @JsonProperty("Total Expense")
    Double getTotalExpense();

    @JsonProperty("Total Discount")
    Double getTotalDiscount();
}
