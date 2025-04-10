package com.capgemini.flight_booking.checkin_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Check-in Service REST API Documentation",
				description = "Check-in Service REST API Documentation",
				version = "1.0",
				contact = @Contact(
						name = "Himanshu Ranjan",
						email = "himanshu2000ranjan@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class CheckinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckinServiceApplication.class, args);
	}

}
