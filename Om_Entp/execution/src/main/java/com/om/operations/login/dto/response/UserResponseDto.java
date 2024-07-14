package com.om.operations.login.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private String userName;

    private Long userId;

    private String token;
}
