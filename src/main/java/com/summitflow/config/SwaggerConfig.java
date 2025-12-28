package com.summitflow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SummitFlow API",
                version = "v1",
                description = "REST API for Tech Conference Management (Speakers, Talks, Tracks)",
                contact = @Contact(
                        name = "Auan Julio Galvão dos Santos",
                        email = "auanjulio13@gmail.com",
                        url = "https://github.com/AuanJulio"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
