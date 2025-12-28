package com.summitflow.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload required for user authentication")
public record LoginRequest(

        @Schema(description = "User's registered email address", example = "john.doe@example.com")
        @NotBlank(message = "Email is required")
        String email,

        @Schema(description = "User's password", example = "secret123")
        @NotBlank(message = "Password is required")
        String password
) {
}
