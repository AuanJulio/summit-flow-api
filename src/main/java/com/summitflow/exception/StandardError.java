package com.summitflow.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record StandardError(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
