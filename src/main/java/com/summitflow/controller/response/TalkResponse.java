package com.summitflow.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TalkResponse(
    Long id,
    String title,
    String summary,
    LocalDateTime startTime,
    Integer durationMin,
    List<SpeakerResponse> speakers,
    List<TrackResponse> tracks
) { }
