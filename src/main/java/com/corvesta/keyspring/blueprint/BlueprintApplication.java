package com.corvesta.keyspring.blueprint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
public class BlueprintApplication  {

	public static void main(String[] args) {
		System.out.println("Inside Main Class");
		SpringApplication.run(BlueprintApplication.class, args);
	}


	


}
