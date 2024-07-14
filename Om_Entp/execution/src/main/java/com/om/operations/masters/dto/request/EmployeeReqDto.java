package com.om.operations.masters.dto.request;
import com.om.operations.masters.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeReqDto {

    private Long id;

    private Role roleId;

    @NotNull(message = "First name can not be null")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    private String lastName;

    @NotNull(message = "Mobile no can not be null")
    private String mobileNo;

    private String aadhaarNo;

    private String photo;

    private Boolean isActive = true;

    private Long createdBy;

    private Long updatedBy;

    private String userName;

    private String password;
}
