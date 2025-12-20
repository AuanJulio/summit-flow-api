package com.summitflow.service;

import com.summitflow.entity.Speaker;
import com.summitflow.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    public List<Speaker> findAll(){
        return speakerRepository.findAll();
    }

    public Speaker save(Speaker speaker){
        return speakerRepository.save(speaker);
    }

    public Optional<Speaker> findById(Long id){
        return speakerRepository.findById(id);
    }

    public Optional<Speaker> update(Long id, Speaker speaker){
        Optional<Speaker> optSpeaker = findById(id);
        if (optSpeaker.isPresent()){
            Speaker updatedSpeaker = optSpeaker.get();
            updatedSpeaker.setName(speaker.getName());
            updatedSpeaker.setEmail(speaker.getEmail());
            updatedSpeaker.setCompany(speaker.getCompany());

            speakerRepository.save(updatedSpeaker);
            return Optional.of(updatedSpeaker);
        }
        return Optional.empty();
    }

    public void deleteById(Long id){
        speakerRepository.deleteById(id);
    }

}
