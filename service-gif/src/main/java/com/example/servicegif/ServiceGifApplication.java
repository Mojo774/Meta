package com.example.servicegif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceGifApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGifApplication.class, args);
	}

}
