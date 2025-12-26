package com.summitflow.controller;

import com.summitflow.controller.request.SpeakerRequest;
import com.summitflow.controller.response.SpeakerResponse;
import com.summitflow.entity.Speaker;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.mapper.SpeakerMapper;
import com.summitflow.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    @GetMapping
    public ResponseEntity<List<SpeakerResponse>> getAll(){
        List<SpeakerResponse> speakers = speakerService.findAll()
                .stream()
                .map(speaker -> SpeakerMapper.toResponse(speaker))
                .toList();

        return ResponseEntity.ok(speakers);
    }

    @PostMapping
    public ResponseEntity<SpeakerResponse> save(@RequestBody SpeakerRequest request){
        Speaker savedSpeaker = speakerService.save(SpeakerMapper.toSpeaker(request));
        return ResponseEntity.ok(SpeakerMapper.toResponse(savedSpeaker));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeakerResponse> getById(@PathVariable Long id){
        return speakerService.findById(id)
                .map(speaker -> ResponseEntity.ok(SpeakerMapper.toResponse(speaker)))
                .orElseThrow(() -> new ResourceNotFoundException("Speaker not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeakerResponse> update(@PathVariable Long id, @RequestBody SpeakerRequest request){
        return speakerService.update(id, SpeakerMapper.toSpeaker(request))
                .map(speaker -> ResponseEntity.ok(SpeakerMapper.toResponse(speaker)))
                .orElseThrow(() -> new ResourceNotFoundException("Speaker not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Speaker> speaker = speakerService.findById(id);
        if (speaker.isPresent()){
            speakerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Speaker not found");
        }
    }

}
