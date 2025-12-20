package com.summitflow.controller.request;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TalkRequest(
        String title,
        String summary,
        LocalDateTime startTime,
        Integer durationMin,
        List<Long> speaker,
        List<Long> tracks
) { }
