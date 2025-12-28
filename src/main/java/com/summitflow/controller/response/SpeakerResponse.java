package com.summitflow.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Public profile of a conference speaker")
public record SpeakerResponse(

        @Schema(description = "Unique identifier of the speaker", example = "10")
        Long id,

        @Schema(description = "Speaker's full name", example = "Auan Julio")
        String name,

        @Schema(description = "Speaker's contact email", example = "auanjulio@gmail.com")
        String email,

        @Schema(description = "Organization represented by the speaker", example = "SummitFlow")
        String company) {
}
