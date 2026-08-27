package com.karim.gdmr_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GdmrBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdmrBackendApplication.class, args);
	}

}
