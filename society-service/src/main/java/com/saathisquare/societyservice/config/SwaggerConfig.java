package com.saathisquare.societyservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Swagger/OpenAPI configuration for the Society Service.
 * 
 * With this bean, Springdoc will generate an OpenAPI description and
 * serve Swagger-UI automatically at /swagger-ui/index.html.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Defines basic API metadata (title, description, version, contact, license, etc.).
     * Springdoc will pick this up and include it in the generated OpenAPI spec.
     */
    @Bean
    public OpenAPI societyOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Housing Society Management API")
                .description("REST endpoints for managing societies, flats, users, and payments.")
                .version("v1.0.0")
                .contact(new Contact()
                    .name("SaathiSquare Team")
                    .email("support@saathisquare.com")
                    .url("https://saathisquare.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                .description("Society-Service Wiki Documentation")
                .url("https://github.com/saathisquare/society-service/wiki"));
    }
}

