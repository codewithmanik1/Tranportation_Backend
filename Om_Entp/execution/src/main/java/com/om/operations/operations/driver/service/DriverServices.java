package com.om.operations.operations.driver.service;

import com.om.operations.operations.driver.dto.request.ShopSalesReqDto;
import org.springframework.http.ResponseEntity;

public interface DriverServices {

    ResponseEntity<?> saveShopSaleByDriver(ShopSalesReqDto shopSalesReqDto);

    ResponseEntity<?> getShopAutoComplete(Long routeId, String searchString);
}
