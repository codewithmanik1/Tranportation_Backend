package com.om.operations.login.dto.filter;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeUserPasswordFilterDto {
    @NotNull(message = "user name cannot be null")
    private String userName;

    @NotNull(message = "password cannot be null")
    private String oldPassword;

    @NotNull(message = "new password cannot be null")
    private String newPassword;
}
