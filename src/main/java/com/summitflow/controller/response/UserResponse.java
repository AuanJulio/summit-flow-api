package com.summitflow.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Response object containing public user details (excluding sensitive data)")
public record UserResponse(

        @Schema(description = "Unique user identifier", example = "42")
        Long id,

        @Schema(description = "User's full name", example = "Auan Julio")
        String name,

        @Schema(description = "User's registered email address", example = "auanjulio@gmail.com")
        String email
) { }
