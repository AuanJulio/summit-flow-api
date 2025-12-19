package com.summitflow.controller.request;

import lombok.Builder;

@Builder
public record SpeakerRequest(String name, String email, String company) {
}
