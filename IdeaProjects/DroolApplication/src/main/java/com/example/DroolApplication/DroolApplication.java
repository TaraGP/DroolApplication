package com.example.DroolApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.DroolApplication")
public class DroolApplication {

	public static void main(String[] args) {

		SpringApplication.run(DroolApplication.class, args);
	}

}
