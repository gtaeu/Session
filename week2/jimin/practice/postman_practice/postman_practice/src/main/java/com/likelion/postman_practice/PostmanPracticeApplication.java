package com.likelion.postman_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PostmanPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostmanPracticeApplication.class, args);
	}

}
