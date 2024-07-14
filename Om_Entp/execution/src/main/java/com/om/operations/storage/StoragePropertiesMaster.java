package com.om.operations.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("storage")
public class StoragePropertiesMaster {
    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";
    private String employeeImages = "./upload-dir/employee-images";
    private String paymentImages = "./upload-dir/payment-images";
}
