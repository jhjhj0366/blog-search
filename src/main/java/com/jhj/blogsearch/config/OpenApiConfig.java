package com.jhj.blogsearch.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.terms-of-service}")
    private String termsOfService;
    @Value("${springdoc.name}")
    private String name;
    @Value("${springdoc.email}")
    private String email;

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info()
                .title("Blog Search Service API")
                .version(appVersion)
                .description("Blog Search Service")
                .termsOfService(termsOfService)
                .contact(new Contact().name(name).email(email));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}