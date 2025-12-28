package com.summitflow.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(description = "Response object containing full details of a Talk")
public record TalkResponse(

        @Schema(description = "Unique talk identifier", example = "1")
        Long id,

        @Schema(description = "Title of the talk", example = "Building Resilient APIs with Spring Boot")
        String title,

        @Schema(description = "Brief summary of the content", example = "In this session, we will explore patterns for fault tolerance...")
        String summary,

        @Schema(
                description = "Scheduled start date and time",
                example = "25/10/2025 14:00:00",
                type = "string",
                pattern = "dd/MM/yyyy HH:mm:ss"
        )
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime startTime,

        @Schema(description = "Duration in minutes", example = "45")
        Integer durationMin,

        @Schema(description = "List of speakers presenting this talk")
        List<SpeakerResponse> speakers,

        @Schema(description = "List of tracks/categories this talk belongs to")
        List<TrackResponse> tracks
) { }
