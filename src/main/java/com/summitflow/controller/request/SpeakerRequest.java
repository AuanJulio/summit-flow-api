package com.summitflow.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Payload required to register or update a Speaker profile")
public record SpeakerRequest(

        @Schema(description = "Speaker's full name", example = "Auan Julio")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @Schema(description = "Speaker's contact email", example = "auanjulio@gmail.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid e-mail format")
        @Size(max = 255, message = "E-mail cannot exceed 255 characters")
        String email,

        @Schema(description = "Company or organization the speaker represents", example = "SummitFlow Tech")
        @NotBlank(message = "Company is required")
        @Size(max = 255, message = "Company cannot exceed 255 characters")
        String company) {
}
