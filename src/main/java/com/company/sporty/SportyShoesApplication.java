package com.company.sporty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.company.sporty")
@EntityScan(basePackages = "com.company.sporty.entity")
@EnableJpaRepositories(basePackages ="com.company.sporty.repository")
public class SportyShoesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SportyShoesApplication.class, args);
		System.err.println("Sporty Shoes website is online");
	}

}
