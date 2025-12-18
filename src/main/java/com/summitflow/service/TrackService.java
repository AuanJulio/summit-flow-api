package com.summitflow.service;

import com.summitflow.entity.Track;
import com.summitflow.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public Optional<Track> findById(Long id){
        return trackRepository.findById(id);
    }

    public Track save(Track track){
        return trackRepository.save(track);
    }

    public Optional<Track> update(Long id, Track track){
        Optional<Track> optTrack = findById(id);
        if (optTrack.isPresent()){
            Track updatedTrack = optTrack.get();
            updatedTrack.setName(track.getName());
            updatedTrack.setDescription(track.getDescription());

            trackRepository.save(updatedTrack);

            return Optional.of(updatedTrack);
        }
        return Optional.empty();
    }

    public void deleteById(Long id){
        trackRepository.deleteById(id);
    }

}
