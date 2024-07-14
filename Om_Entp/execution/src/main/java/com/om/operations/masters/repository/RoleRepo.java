package com.om.operations.masters.repository;


import com.om.operations.masters.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
