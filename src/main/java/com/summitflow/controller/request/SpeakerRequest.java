package com.summitflow.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SpeakerRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid e-mail format")
        @Size(max = 255, message = "E-mail cannot exceed 255 characters")
        String email,

        @NotBlank(message = "Company is required")
        @Size(max = 255, message = "Company cannot exceed 255 characters")
        String company) {
}
