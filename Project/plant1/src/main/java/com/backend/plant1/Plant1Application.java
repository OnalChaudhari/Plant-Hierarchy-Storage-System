package com.backend.plant1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.backend.plant1")
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.backend.plant1")
@EntityScan(basePackages = "com.backend.plant1")
@ComponentScan(basePackages = "com.backend.plant1")
public class Plant1Application {

	public static void main(String[] args) {
		SpringApplication.run(Plant1Application.class, args);
	}

}
