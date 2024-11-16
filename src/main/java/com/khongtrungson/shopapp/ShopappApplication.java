package com.khongtrungson.shopapp;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.khongtrungson.shopapp.controllers.CategoryController;
import com.khongtrungson.shopapp.controllers.OrderController;
import com.khongtrungson.shopapp.controllers.ProductController;
import com.khongtrungson.shopapp.controllers.UserController;
import com.khongtrungson.shopapp.dtos.requests.CategoryDTO;
import com.khongtrungson.shopapp.dtos.requests.InsertUserRequest;
import com.khongtrungson.shopapp.dtos.requests.OrderDTO;
import com.khongtrungson.shopapp.dtos.requests.ProductDTO;
import com.khongtrungson.shopapp.entities.Category;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.repositories.CategoryRepository;
import com.khongtrungson.shopapp.repositories.ProductRepository;
import com.khongtrungson.shopapp.repositories.UserRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.Properties;

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

	private EntityManager entityManager;

	public static void main(String[] args){
		var context = SpringApplication.run(ShopappApplication.class, args);
//		SpringApplication app= new SpringApplication(ShopappApplication.class);
//		Properties props = new Properties();
//		app.run(args);

	}
	@Transactional
	void test(CategoryRepository categoryRepository, ProductRepository productRepository) {
		Category category = new Category();
		Product product= new Product();
		product.setCategory(category);
		categoryRepository.save(category);
		productRepository.save(product);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserController userController,
//										OrderController orderController,
//										ProductController productController,
//										CategoryController categoryController) {
//		return args -> {
//			OrderDTO orderDTO = new OrderDTO();
//			// can 1 user 1 product
//			InsertUserRequest userRequest = new InsertUserRequest();
//			userRequest.setAddress("a");
//			userRequest.setPassword("1234");
//			userRequest.setRetypePassword("1234");
//			userRequest.setPhoneNumber("0865390256");
//			userRequest.setFullName("trung son");
//			userRequest.setDateOfBirth(new Date());
//			userController.createUser(userRequest);
//			CategoryDTO categoryDTO = new CategoryDTO();
//			categoryDTO.setName("aaa");
//			categoryController.createCategory(categoryDTO);
//			ProductDTO productDTO = new ProductDTO();
//			productDTO.setName("asas");
//			productDTO.setDescription("asas");
//			productDTO.setDescription("asas");
//			productDTO.setPrice((float)12);
//			productDTO.setQuantity(5);
//			productDTO.setCategoryId((long)1);
//			productController.createProduct(productDTO);
//
//
//
//
//		};
//	}


}
