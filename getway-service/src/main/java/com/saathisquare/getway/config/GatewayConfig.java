package com.saathisquare.getway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth-service", r -> r.path("/auth/**").uri("lb://AUTH-SERVICE"))
				.route("rbac-service", r -> r.path("/rbac/**").uri("lb://RBAC-SERVICE"))
				.route("society-service", r -> r.path("/society/**").uri("lb://SOCIETY-SERVICE")).build();
	}

}
