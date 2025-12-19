package com.summitflow.controller.response;

import lombok.Builder;

@Builder
public record SpeakerResponse(Long id, String name, String email, String company) {
}
