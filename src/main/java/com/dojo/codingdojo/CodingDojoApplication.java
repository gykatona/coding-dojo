package com.dojo.codingdojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CodingDojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingDojoApplication.class, args);
	}

}
