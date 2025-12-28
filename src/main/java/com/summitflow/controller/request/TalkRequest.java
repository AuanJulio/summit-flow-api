package com.summitflow.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(description = "Payload required to create or update a Talk")
public record TalkRequest(

        @Schema(description = "The official title of the talk", example = "Advanced Spring Boot Architecture")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String title,

        @Schema(description = "A brief summary describing the content of the talk", example = "This session dives deep into the internal mechanisms of Spring Boot...")
        @NotBlank(message = "Summary is required")
        @Size(max = 2000, message = "Summary cannot exceed 2000 characters")
        String summary,

        @Schema(
                description = "The scheduled start date and time. Format: dd/MM/yyyy HH:mm:ss",
                example = "25/10/2025 14:30:00",
                type = "string",
                pattern = "dd/MM/yyyy HH:mm:ss"
        )
        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime startTime,

        @Schema(description = "The duration of the talk in minutes", example = "50")
        @NotNull(message = "Duration is required")
        Integer durationMin,

        @Schema(description = "List of existing Speaker IDs that will present this talk", example = "[1, 2]")
        @NotEmpty(message = "At least one speaker is required")
        List<Long> speakers,

        @Schema(description = "List of existing Track IDs (categories) this talk belongs to", example = "[3]")
        @NotEmpty(message = "At least one track is required")
        List<Long> tracks
) { }
