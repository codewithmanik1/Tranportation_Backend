package com.om.operations.operations.driver.controller;

import com.om.operations.operations.driver.dto.request.ShopSalesReqDto;
import com.om.operations.operations.driver.service.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driverOperations")
public class DriverOperationsController {

    @Autowired
    private DriverServices driverServices;


    @PostMapping("/saveShopSaleByDriver")
    public ResponseEntity<?> saveShopSaleByDriver(@RequestBody ShopSalesReqDto shopSalesReqDto){
        return driverServices.saveShopSaleByDriver(shopSalesReqDto);
    }

    @GetMapping("/getShopAutoComplete/{routeId}/{searchString}")
    public ResponseEntity<?> getShopAutoComplete(@PathVariable Long routeId, @PathVariable String searchString){
        return driverServices.getShopAutoComplete(routeId, searchString);
    }
}
