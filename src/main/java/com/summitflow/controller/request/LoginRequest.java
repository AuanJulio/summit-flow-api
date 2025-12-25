package com.summitflow.controller.request;

public record LoginRequest(
        String email,
        String password
) {
}
