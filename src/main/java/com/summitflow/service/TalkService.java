package com.summitflow.service;

import com.summitflow.entity.Speaker;
import com.summitflow.entity.Talk;
import com.summitflow.entity.Track;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.repository.SpeakerRepository;
import com.summitflow.repository.TalkRepository;
import com.summitflow.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TalkService {

    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;
    private final TrackRepository trackRepository;
    private final SpeakerService speakerService;
    private final TrackService trackService;

    public List<Talk> findAll(){
        return talkRepository.findAll();
    }

    public Talk save(Talk talk){
        talk.setSpeakers(new HashSet<>(this.findSpeakers(talk.getSpeakers())));
        talk.setTracks(new HashSet<>(this.findTracks(talk.getTracks())));
        return talkRepository.save(talk);
    }

    public Optional<Talk> findById(Long id){
        return talkRepository.findById(id);
    }

    public Optional<Talk> update(Long id, Talk talk){
        Optional<Talk> optTalk = talkRepository.findById(id);
        if (optTalk.isPresent()) {
            Talk updatedTalk = optTalk.get();
            updatedTalk.setTitle(talk.getTitle());
            updatedTalk.setSummary(talk.getSummary());
            updatedTalk.setStartTime(talk.getStartTime());
            updatedTalk.setDurationMin(talk.getDurationMin());

            updatedTalk.getSpeakers().clear();
            updatedTalk.getSpeakers().addAll(this.findSpeakers(talk.getSpeakers()));

            updatedTalk.getTracks().clear();
            updatedTalk.getTracks().addAll(this.findTracks(talk.getTracks()));

            return Optional.of(talkRepository.save(updatedTalk));
        }
        return Optional.empty();
    }

    public void deleteById(Long id){
        talkRepository.deleteById(id);
    }

    public List<Talk> findByTracks(List<Long> trackIds){
        return talkRepository.findByTracksQuery(trackIds);
    }

    private List<Speaker> findSpeakers(Set<Speaker> speakers){
        if (speakers == null || speakers.isEmpty()) {
            return List.of();
        }

        Set<Long> speakerIds = speakers.stream()
                .map(speaker -> speaker.getId())
                .collect(Collectors.toSet());

        List<Speaker> foundSpeakers = speakerRepository.findAllById(speakerIds);

        Set<Long> foundIds = foundSpeakers.stream()
                .map(speaker -> speaker.getId())
                .collect(Collectors.toSet());

        Set<Long> missingIds = speakerIds.stream()
                .filter(id -> !foundIds.contains(id))
                .collect(Collectors.toSet());

        if (!missingIds.isEmpty()) {
            throw new ResourceNotFoundException("Speakers with ids " + missingIds + " not found");
        }

        return foundSpeakers;
    }

    private List<Track> findTracks(Set<Track> tracks){
        if (tracks == null || tracks.isEmpty()) {
            return List.of();
        }

        Set<Long> trackIds = tracks.stream()
                .map(Track::getId)
                .collect(Collectors.toSet());

        List<Track> foundTracks = trackRepository.findAllById(trackIds);

        Set<Long> foundIds = foundTracks.stream()
                .map(Track::getId)
                .collect(Collectors.toSet());

        Set<Long> missingId = trackIds.stream()
                .filter(id -> !foundIds.contains(id))
                .collect(Collectors.toSet());

        if (!missingId.isEmpty()) {
            throw new ResourceNotFoundException("Track with id " + missingId + " not found");
        }

        return foundTracks;
    }

}
