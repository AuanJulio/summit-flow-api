package com.summitflow.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TalkRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String title,

        @NotBlank(message = "Summary is required")
        @Size(max = 2000, message = "Summary cannot exceed 2000 characters")
        String summary,

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime startTime,

        @NotNull(message = "Duration is required")
        Integer durationMin,

        @NotEmpty(message = "At least one speaker is required")
        List<Long> speakers,

        @NotEmpty(message = "At least one track is required")
        List<Long> tracks
) { }
