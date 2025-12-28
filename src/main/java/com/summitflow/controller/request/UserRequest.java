package com.summitflow.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Payload required for user registration")
public record UserRequest(

        @Schema(description = "User's full name", example = "Auan Julio")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @Schema(description = "User's email address (used for login)", example = "auanjulio@gmail.com")
        @NotBlank(message = "E-mail is required")
        @Size(max = 255, message = "E-mail cannot exceed 255 characters")
        @Email(message = "Invalid e-mail format")
        String email,

        @Schema(description = "User's secure password (min 6 characters)", example = "password123")
        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
        String password
) { }
