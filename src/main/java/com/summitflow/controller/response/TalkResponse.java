package com.summitflow.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TalkResponse(
    Long id,
    String title,
    String summary,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime startTime,
    Integer durationMin,
    List<SpeakerResponse> speakers,
    List<TrackResponse> tracks
) { }
