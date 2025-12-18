package com.summitflow.mapper;

import com.summitflow.controller.request.TrackRequest;
import com.summitflow.controller.response.TrackResponse;
import com.summitflow.entity.Track;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrackMapper {

    public static TrackResponse toResponse(Track track){
        return TrackResponse.builder()
                .id(track.getId())
                .name(track.getName())
                .description(track.getDescription())
                .build();
    }

    public static Track toTrack(TrackRequest response){
        return Track.builder()
                .name(response.name())
                .description(response.description())
                .build();
    }

}
