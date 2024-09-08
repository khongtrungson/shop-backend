package com.khongtrungson.shopapp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImp")
@OpenAPIDefinition(
		info = @Info(
				title = "Documentation for Backend System",
				description = "REST API Documentation",
				version = "v1",
				contact = @Contact(
					name = "Khổng Trung Sơn",
					email = "khongtrungson2004@gmail.com",
					url = "https://www.facebook.com/khongtrungson2004/"
				),
				license = @License(
						name = "No license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Documentation",
				url = "...."
		)
)
public class ShopappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopappApplication.class, args);
	}

}
