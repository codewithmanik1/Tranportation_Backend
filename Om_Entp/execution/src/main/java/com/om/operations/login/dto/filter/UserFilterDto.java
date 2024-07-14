package com.om.operations.login.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFilterDto {

    private String userName;//this key is for delete user only

    private Long page;

    private Long size;

    private String searching;
}
