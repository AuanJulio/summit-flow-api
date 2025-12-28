package com.summitflow.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Response object representing a knowledge Track (category)")
public record TrackResponse(

        @Schema(description = "Unique track identifier", example = "5")
        Long id,

        @Schema(description = "Name of the track", example = "DevOps")
        String name,

        @Schema(description = "Description of the track's focus area", example = "CI/CD pipelines, containerization, and infrastructure as code.")
        String description) {
}
