package com.summitflow.mapper;

import com.summitflow.controller.request.SpeakerRequest;
import com.summitflow.controller.response.SpeakerResponse;
import com.summitflow.entity.Speaker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpeakerMapper {

    public static SpeakerResponse toResponse(Speaker speaker){
        return SpeakerResponse.builder()
                .id(speaker.getId())
                .name(speaker.getName())
                .email(speaker.getEmail())
                .company(speaker.getCompany())
                .build();
    }

    public static Speaker toSpeaker(SpeakerRequest request){
        return Speaker.builder()
                .name(request.name())
                .email(request.email())
                .company(request.company())
                .build();
    }

}
