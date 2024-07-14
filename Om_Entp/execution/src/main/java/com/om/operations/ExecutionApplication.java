package com.om.operations;

import com.om.operations.storage.StoragePropertiesMaster;
import com.om.operations.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StoragePropertiesMaster.class)
public class ExecutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecutionApplication.class, args);
	}


	@Bean
	CommandLineRunner initMaster(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
