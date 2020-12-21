package com.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FleetManagement {

	public static void main(String[] args) {
		SpringApplication.run(FleetManagement.class, args);
	}
}
