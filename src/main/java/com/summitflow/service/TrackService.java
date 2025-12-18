package com.summitflow.service;

import com.summitflow.entity.Track;
import com.summitflow.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public Track findById(Long id){
        return trackRepository.findById(id).get();
    }

    public Track save(Track track){
        return trackRepository.save(track);
    }

    public Track update(Long id, Track track){
        track.setId(id);
        return trackRepository.save(track);
    }

    public void deleteById(Long id){
        trackRepository.deleteById(id);
    }

}
