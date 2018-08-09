package com.corvesta.keyspring.blueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.internal.EnableZipkinServer;

@EnableDiscoveryClient
@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
@EnableAutoConfiguration
@EnableZipkinServer


public class BlueprintApplication  {

	public static void main(String[] args) {
		System.out.println("Inside Main Class");
		SpringApplication.run(BlueprintApplication.class, args);
	}


	


}
