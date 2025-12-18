package com.summitflow.controller.request;

import lombok.Builder;

@Builder
public record TrackRequest(String name, String description) {
}
