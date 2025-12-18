package com.summitflow.controller.response;

import lombok.Builder;

@Builder
public record TrackResponse(Long id, String name, String description) {
}
