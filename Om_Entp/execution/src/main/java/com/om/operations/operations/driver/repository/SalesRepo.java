package com.om.operations.operations.driver.repository;

import com.om.operations.operations.driver.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SalesRepo extends JpaRepository<Sales, Long> {

    @Query(value = "select * from fn_get_shop_auto_complete(?1, ?2)", nativeQuery = true)
    List<Map<String, Object>> getShopAutoComplete(Long routeId, String searchString);
}
