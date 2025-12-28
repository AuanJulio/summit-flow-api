package com.summitflow.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Response object containing the authentication token")
public record LoginResponse(

        @Schema(
                description = "JWT Access Token to be used in the 'Authorization' header (Bearer scheme)",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyQGVtYWlsLmNvbSIsImV4cCI6MTYzODQ2..."
        )
        String token

) {
}
