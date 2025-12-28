package com.summitflow.mapper;

import com.summitflow.controller.request.TalkRequest;
import com.summitflow.controller.response.SpeakerResponse;
import com.summitflow.controller.response.TalkResponse;
import com.summitflow.controller.response.TrackResponse;
import com.summitflow.entity.Speaker;
import com.summitflow.entity.Talk;
import com.summitflow.entity.Track;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class TalkMapper {

    public static Talk toTalk(TalkRequest talkRequest){

        Set<Speaker> speakers = talkRequest.speakers()
                .stream()
                .map(speakerId -> Speaker.builder().id(speakerId).build())
                .collect(Collectors.toSet());

        Set<Track> tracks = talkRequest.tracks()
                .stream()
                .map(trackId -> Track.builder().id(trackId).build())
                .collect(Collectors.toSet());

        return Talk.builder()
                .title(talkRequest.title())
                .summary(talkRequest.summary())
                .startTime(talkRequest.startTime())
                .durationMin(talkRequest.durationMin())
                .speakers(speakers)
                .tracks(tracks)
                .build();
    }

    public static TalkResponse toResponse(Talk talk){

        List<SpeakerResponse> speakers = talk.getSpeakers()
                .stream()
                .map(speaker -> SpeakerMapper.toResponse(speaker))
                .toList();

        List<TrackResponse> tracks = talk.getTracks()
                .stream()
                .map(track -> TrackMapper.toResponse(track))
                .toList();

        return TalkResponse.builder()
                .id(talk.getId())
                .title(talk.getTitle())
                .summary(talk.getSummary())
                .startTime(talk.getStartTime())
                .durationMin(talk.getDurationMin())
                .speakers(speakers)
                .tracks(tracks)
                .build();
    }

}
