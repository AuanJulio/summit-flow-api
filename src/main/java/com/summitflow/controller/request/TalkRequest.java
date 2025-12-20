package com.summitflow.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TalkRequest(
        String title,
        String summary,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime startTime,
        Integer durationMin,
        List<Long> speakers,
        List<Long> tracks
) { }
