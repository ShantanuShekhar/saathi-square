package com.saathisquare.authservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RbacServiceProperties.class)
public class RbacConfig {
}
