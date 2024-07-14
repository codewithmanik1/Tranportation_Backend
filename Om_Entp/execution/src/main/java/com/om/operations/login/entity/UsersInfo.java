//package com.om.operations.login.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Setter
//@Getter
//@Table(name = "users")
//public class UsersInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull(message = "user name cannot be null")
//    @Column(name = "user_name", unique = true)
//    private String userName;
//
//    private String password;
//
//    private LocalDateTime createdDateTime=LocalDateTime.now();
//
//    private LocalDateTime lastUpdatedDateTime=LocalDateTime.now();
//
//    private Boolean approvalFlag = false;
//
//    private Boolean loginStatus = false;
//
//    private Boolean isActive = true;
//
//    private Boolean isDeleted = false;
//}
