package com.summitflow.controller.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String token
) {
}
