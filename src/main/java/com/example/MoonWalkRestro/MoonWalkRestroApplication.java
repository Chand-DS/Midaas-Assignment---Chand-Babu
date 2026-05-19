package com.example.MoonWalkRestro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoonWalkRestroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonWalkRestroApplication.class, args);
	}

}
