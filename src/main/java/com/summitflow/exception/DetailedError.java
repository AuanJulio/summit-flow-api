package com.summitflow.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Schema(description = "Extended error structure for validation failures (400 Bad Request)")
public record DetailedError(

        @Schema(
                description = "Timestamp of the error occurrence",
                example = "27/12/2025 10:35:00",
                type = "string",
                pattern = "dd/MM/yyyy HH:mm:ss"
        )
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,

        @Schema(description = "HTTP Status code", example = "400")
        Integer status,

        @Schema(description = "Error type", example = "Bad Request")
        String error,

        @Schema(description = "General error message", example = "Validation failed due to invalid request parameters")
        String message,

        @Schema(description = "Request path that caused the error", example = "/api/v1/talks")
        String path,

        @Schema(
                description = "Map of field names and their specific validation error messages",
                example = "{\"title\": \"Title is required\", \"durationMin\": \"Duration must be at least 15 minutes\"}"
        )
        Map<String, String> errors
) {
}
