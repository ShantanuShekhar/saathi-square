package com.saathisquare.authservice.config;

import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saathisquare.authservice.client.RbacClient;

@Configuration
public class FeignClientFactory {

	@Bean
	public RbacClient rbacClient(FeignClientBuilder feignClientBuilder, RbacServiceProperties properties) {
		return feignClientBuilder.forType(RbacClient.class, "rbac-service").url(properties.baseUrl()).build();
	}
}
