package com.summitflow.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Payload required to create or update a knowledge Track (category)")
public record TrackRequest(

        @Schema(description = "The name of the track", example = "Cloud Computing")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @Schema(description = "A detailed description of the topics covered in this track", example = "Sessions related to AWS, Azure, Google Cloud, and cloud-native architecture.")
        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description) {
}
