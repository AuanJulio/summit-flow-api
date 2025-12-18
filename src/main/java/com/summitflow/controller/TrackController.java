package com.summitflow.controller;

import com.summitflow.controller.request.TrackRequest;
import com.summitflow.controller.response.TrackResponse;
import com.summitflow.entity.Track;
import com.summitflow.mapper.TrackMapper;
import com.summitflow.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public ResponseEntity<List<TrackResponse>> getAll(){
        List<TrackResponse> tracks = trackService.findAll().stream()
                .map(track -> TrackMapper.toResponse(track))
                .toList();

        return ResponseEntity.ok().body(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponse> getById(@PathVariable Long id){
        return trackService.findById(id)
                .map(track -> ResponseEntity.ok(TrackMapper.toResponse(track)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrackResponse> save(@RequestBody TrackRequest request){
        Track savedTrack = trackService.save(TrackMapper.toTrack(request));
        return ResponseEntity.ok(TrackMapper.toResponse(savedTrack));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackResponse> update(@PathVariable Long id, @RequestBody TrackRequest request){
        return trackService.update(id, TrackMapper.toTrack(request))
                .map(t -> ResponseEntity.ok(TrackMapper.toResponse(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Track> track = trackService.findById(id);
        if (track.isPresent()){
            trackService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
