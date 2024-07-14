package com.om.operations.masters.repository;

import com.om.operations.masters.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUserName(String username);

    Optional<Employee> findByUserNameAndIsActiveTrue(String username);

//    @Query(value = "select * from fn_users_get_all_users_list(?1,?2,?3)", nativeQuery = true)
//    List<UserListResponseDto> getUserList(Long page, Long size, String searching);

//    @Query(value = "select * from fn_users_get_all_users_list_count(?1)", nativeQuery = true)
//    Long getUserListCount(String searching);
}
