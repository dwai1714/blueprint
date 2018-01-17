package com.corvesta.keyspring.blueprint.config.core;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/*
 * Create a Singleton for RestTemplate. If this is not done Sleuth will not put trace-itemtId
 */
@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}

}
