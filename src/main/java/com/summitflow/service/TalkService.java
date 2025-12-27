package com.summitflow.service;

import com.summitflow.entity.Speaker;
import com.summitflow.entity.Talk;
import com.summitflow.entity.Track;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TalkService {

    private final TalkRepository talkRepository;
    private final SpeakerService speakerService;
    private final TrackService trackService;

    public List<Talk> findAll(){
        return talkRepository.findAll();
    }

    public Talk save(Talk talk){
        talk.setSpeakers(this.findSpeakers(talk.getSpeakers()));
        talk.setTracks(this.findTracks(talk.getTracks()));
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

    private List<Speaker> findSpeakers(List<Speaker> speakers){
        List<Speaker> foundSpeakers = new ArrayList<>();
        speakers.forEach(speaker -> {
            Speaker speakerFound = speakerService.findById(speaker.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Speaker with id" + speaker.getId() + " not found"));
            foundSpeakers.add(speakerFound);
        });
        return foundSpeakers;
    }

    private List<Track> findTracks(List<Track> tracks){
        List<Track> foundTracks = new ArrayList<>();
        tracks.forEach(track -> {
            Track trackFound = trackService.findById(track.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Track with id" + track.getId() + " not found"));
            foundTracks.add(trackFound);
        });
        return foundTracks;
    }

}
