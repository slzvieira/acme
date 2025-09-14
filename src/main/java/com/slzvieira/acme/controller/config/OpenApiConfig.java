package com.slzvieira.acme.controller.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI acmeAPI() {
        return new OpenAPI().info(new Info()
                .title("ACME Company API")
                .description("ACME Company Rest Service Application")
                .version(this.getClass().getPackage().getImplementationVersion()));
    }
}
