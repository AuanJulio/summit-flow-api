package com.summitflow.controller;

import com.summitflow.controller.request.TalkRequest;
import com.summitflow.controller.response.TalkResponse;
import com.summitflow.entity.Talk;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.mapper.TalkMapper;
import com.summitflow.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/talks")
@RequiredArgsConstructor
public class TalkController {

    private final TalkService talkService;

    @GetMapping
    public ResponseEntity<List<TalkResponse>> getAll(){
        List<TalkResponse> talks = talkService.findAll()
                .stream()
                .map(talk -> TalkMapper.toResponse(talk))
                .toList();

        return ResponseEntity.ok(talks);
    }

    @PostMapping
    public ResponseEntity<TalkResponse> save(@RequestBody TalkRequest request){
        Talk savedTalk = talkService.save(TalkMapper.toTalk(request));
        return ResponseEntity.ok(TalkMapper.toResponse(savedTalk));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalkResponse> findById(@PathVariable Long id){
        return talkService.findById(id)
                .map(talk -> ResponseEntity.ok(TalkMapper.toResponse(talk)))
                .orElseThrow(() -> new ResourceNotFoundException("Talk not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TalkResponse> update(@PathVariable Long id, @RequestBody TalkRequest request){
        return talkService.update(id, TalkMapper.toTalk(request))
                .map(talk -> ResponseEntity.ok(TalkMapper.toResponse(talk)))
                .orElseThrow(() -> new ResourceNotFoundException("Talk not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Talk> talk = talkService.findById(id);
        if (talk.isPresent()){
            talkService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Talk not found");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<TalkResponse>> findByTracks(@RequestParam List<Long> trackIds){
        List<TalkResponse> tracks = talkService.findByTracks(trackIds)
                .stream()
                .map(track -> TalkMapper.toResponse(track))
                .toList();

        return ResponseEntity.ok(tracks);
    }

}
