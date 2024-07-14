package com.om.operations.login.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "User Name", "Created Date time", "Last Updated Date Time", "Active"
})
public interface UserListResponseDto {

    @JsonProperty("User Name")
    String getUsername();

    @JsonProperty("Created Date time")
    String getCreatedDatetime();

    @JsonProperty("Last Updated Date Time")
    String getLastUpdatedDatetime();

    @JsonProperty("Active")
    Boolean getActive();
}
