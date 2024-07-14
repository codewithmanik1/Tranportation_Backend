package com.om.operations.masters.entity;


import com.om.operations.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @Column(name = "first_name")
    @NotNull(message = "First name can not be null")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name can not be null")
    private String lastName;

    @Column(name = "mobile_no")
    @NotNull(message = "Mobile no can not be null")
    private String mobileNo;

    @Column(name = "aadhaar_no")
    private String aadhaarNo;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_delete")
    private Boolean isDelete = false;
}
