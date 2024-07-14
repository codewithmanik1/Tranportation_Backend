package com.om.operations.login.service;

import com.om.operations.login.dto.filter.ChangeUserPasswordFilterDto;
import com.om.operations.login.dto.filter.UserFilterDto;
import com.om.operations.login.dto.request.LoginDto;
import com.om.operations.login.dto.request.UsersInfoRequestDto;
import org.springframework.http.ResponseEntity;

public interface UsersInfoServices {

    ResponseEntity<?> login(LoginDto loginDto);

//    ResponseEntity<?> getUserList(UserFilterDto filterDto);
//
//    ResponseEntity<?> changeUserPassword(ChangeUserPasswordFilterDto filterDto);
//
//    ResponseEntity<?> deleteUser(UserFilterDto filterDto);
}
