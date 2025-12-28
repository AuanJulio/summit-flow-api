package com.summitflow.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Schema(description = "Standard error payload for 4xx and 5xx responses (excluding validation errors)")
public record StandardError(

        @Schema(
                description = "Timestamp of the error occurrence",
                example = "27/12/2025 10:30:00",
                type = "string",
                pattern = "dd/MM/yyyy HH:mm:ss"
        )
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,

        @Schema(description = "HTTP Status code", example = "404")
        Integer status,

        @Schema(description = "Error classification", example = "Resource Not Found")
        String error,

        @Schema(description = "Detailed error message", example = "Talk with ID 1 not found")
        String message,

        @Schema(description = "Request path that caused the error", example = "/api/v1/talks/1")
        String path
) {
}
