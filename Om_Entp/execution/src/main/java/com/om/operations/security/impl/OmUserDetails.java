package com.om.operations.security.impl;

import com.om.operations.masters.entity.Employee;
import com.om.operations.masters.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OmUserDetails implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Employee users = employeeRepo.findByUserNameAndIsActiveTrue(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
            return UserDetailsImpl.build(users);
    }
}