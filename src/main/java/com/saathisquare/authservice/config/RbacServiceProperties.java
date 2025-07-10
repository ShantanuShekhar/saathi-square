package com.saathisquare.authservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rbac-service")
public record RbacServiceProperties(String baseUrl) {}
