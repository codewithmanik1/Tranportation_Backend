package com.om.operations.login.controller;

import com.om.operations.login.dto.filter.ChangeUserPasswordFilterDto;
import com.om.operations.login.dto.filter.UserFilterDto;
import com.om.operations.login.dto.request.LoginDto;
import com.om.operations.login.service.UsersInfoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UsersInfoController {
    @Autowired
    private UsersInfoServices usersInfoServices;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return usersInfoServices.login(loginDto);
    }


//    @PostMapping("/changePassword")
//    public ResponseEntity<?> changeUserPassword(@RequestBody @Valid ChangeUserPasswordFilterDto filterDto) {
//        return usersInfoServices.changeUserPassword(filterDto);
//    }
//
//
//    @PostMapping("/userListing")
//    public ResponseEntity<?> getUserList(@RequestBody @Valid UserFilterDto filterDto) {
//        return usersInfoServices.getUserList(filterDto);
//    }
//
//
//    @DeleteMapping("/deleteUser")
//    public ResponseEntity<?> deleteUser(@RequestBody @Valid UserFilterDto filterDto) {
//        return usersInfoServices.deleteUser(filterDto);
//    }

}
