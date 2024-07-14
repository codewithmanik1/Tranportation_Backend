package com.om.operations.login.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersInfoRequestDto {

   @NotNull(message = "user name cannot be null")
   private String userName;

    @NotNull(message = "password cannot be null")
    private String password;
}
