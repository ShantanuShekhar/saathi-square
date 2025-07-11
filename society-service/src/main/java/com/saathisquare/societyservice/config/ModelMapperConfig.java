package com.saathisquare.societyservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Registers a single ModelMapper instance in the Spring context.
     * You can inject ModelMapper wherever we need to map between DTOs and entities.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
