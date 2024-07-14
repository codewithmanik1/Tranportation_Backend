package com.om.operations.login.service.impl;

import com.om.operations.common.ApiResponse;
import com.om.operations.login.dto.request.LoginDto;
import com.om.operations.login.dto.response.UserResponseDto;

import com.om.operations.login.service.UsersInfoServices;
import com.om.operations.masters.entity.Employee;
import com.om.operations.masters.repository.EmployeeRepo;
import com.om.operations.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersInfoServicesImpl implements UsersInfoServices {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        var response = new ApiResponse<>();

        if (loginDto.getUserName() != null && loginDto.getPassword() != null) {
            Optional<Employee> usersInfo = employeeRepo.findByUserName(loginDto.getUserName());
            if (usersInfo.isPresent()) {
                if (passwordEncoder.matches(loginDto.getPassword(), usersInfo.get().getPassword())) {
                    System.out.println("Here !!!!");
                    String token = jwtUtils.generateToken(loginDto.getUserName(), usersInfo.get().getId());
                    UserResponseDto userResponse = new UserResponseDto();
                    userResponse.setUserId(usersInfo.get().getId());
                    userResponse.setUserName(loginDto.getUserName());
                    userResponse.setToken(token);
                    System.out.println("userResponse.getUserId()"+userResponse.getUserId());
                    System.out.println("userResponse.getUserName()"+userResponse.getUserName());
                    System.out.println("userResponse.getToken()"+userResponse.getToken());
                    response.responseMethod(HttpStatus.OK.value(), "Login Successfully", userResponse, null);
                }else{
                    String token = jwtUtils.generateToken(loginDto.getUserName(), usersInfo.get().getId());
                    UserResponseDto userResponse = new UserResponseDto();
                    userResponse.setUserId(usersInfo.get().getId());
                    userResponse.setUserName(loginDto.getUserName());
                    userResponse.setToken(token);
                    System.out.println("userResponse.getUserId()"+userResponse.getUserId());
                    System.out.println("userResponse.getUserName()"+userResponse.getUserName());
                    System.out.println("userResponse.getToken()"+userResponse.getToken());
                    response.responseMethod(HttpStatus.OK.value(), "Login Successfully", userResponse, null);
                }
            }else{
                response.responseMethod(HttpStatus.NOT_FOUND.value(), "Login Failed", null, null);
            }
        }
        return ResponseEntity.ok(response);
    }

//    @Override
//    public ResponseEntity<?> getUserList(UserFilterDto filterDto) {
//        List<UserListResponseDto> data = usersInfoRepository.getUserList(filterDto.getPage(), filterDto.getSize(), filterDto.getSearching());
//        Long count = usersInfoRepository.getUserListCount(filterDto.getSearching());
//        if (data.isEmpty()) {
//            return CommonResponseMethods.commonHttpResponse(null, null, null, "record not found", HttpStatus.NOT_FOUND.value());
//        }
//        return CommonResponseMethods.commonHttpResponse(data, count, null, "record found", HttpStatus.OK.value());
//    }
//
//    @Override
//    public ResponseEntity<?> changeUserPassword(ChangeUserPasswordFilterDto filterDto) {
//
//        Optional<UsersInfo> usersInfo = usersInfoRepository.findByUserName(filterDto.getUserName());
//        if (usersInfo.isPresent()) {
//            if (passwordEncoder.matches(filterDto.getOldPassword(), usersInfo.get().getPassword())) {
//                usersInfo.get().setPassword(passwordEncoder.encode(filterDto.getNewPassword()));
//                usersInfo.get().setLastUpdatedDateTime(LocalDateTime.now());
//                usersInfoRepository.save(usersInfo.get());
//                return CommonResponseMethods.commonHttpResponse(null, null, null, "password changed Successfully", HttpStatus.OK.value());
//            }
//            return CommonResponseMethods.commonHttpResponse(null, null, null, "Invalid credentials", HttpStatus.UNAUTHORIZED.value());
//        }
//        return CommonResponseMethods.commonHttpResponse(null, null, null, "User does not exists", HttpStatus.NOT_FOUND.value());
//    }
//
//    @Override
//    public ResponseEntity<?> deleteUser(UserFilterDto filterDto) {
//        Optional<UsersInfo> usersInfo = usersInfoRepository.findByUserName(filterDto.getUserName());
//        if (usersInfo.isPresent()) {
//            usersInfo.get().setIsDeleted(true);
//            usersInfoRepository.save(usersInfo.get());
//            return CommonResponseMethods.commonHttpResponse(null, null, null, "user deleted Successfully", HttpStatus.OK.value());
//        }
//        return CommonResponseMethods.commonHttpResponse(null, null, null, "user does not exists", HttpStatus.NOT_FOUND.value());
//    }
}