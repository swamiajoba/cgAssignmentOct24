package com.manju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceProjectApplication.class, args);
	}

}
