package com.om.operations.masters.repository;

import com.om.operations.masters.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shops, Long> {
}
