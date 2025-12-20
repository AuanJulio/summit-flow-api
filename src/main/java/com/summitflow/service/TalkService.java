package com.summitflow.service;

import com.summitflow.entity.Speaker;
import com.summitflow.entity.Talk;
import com.summitflow.entity.Track;
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

    private List<Speaker> findSpeakers(List<Speaker> speakers){
        List<Speaker> foundSpeakers = new ArrayList<>();
        speakers.forEach(speaker -> {
            speakerService.findById(speaker.getId()).ifPresent(foundSpeakers::add);
        });
        return foundSpeakers;
    }

    private List<Track> findTracks(List<Track> tracks){
        List<Track> foundTracks = new ArrayList<>();
        tracks.forEach(track -> {
            trackService.findById(track.getId()).ifPresent(foundTracks::add);
        });
        return foundTracks;
    }

}
