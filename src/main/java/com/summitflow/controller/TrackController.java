package com.summitflow.controller;

import com.summitflow.entity.Track;
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
    public ResponseEntity<List<Track>> getAll(){
        List<Track> tracks = trackService.findAll();
        return ResponseEntity.ok().body(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getById(@PathVariable Long id){
        return trackService.findById(id)
                .map(track -> ResponseEntity.ok(track))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Track> save(@RequestBody Track track){
        Track savedTrack = trackService.save(track);
        return ResponseEntity.ok(savedTrack);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> update(@PathVariable Long id, @RequestBody Track track){
        return trackService.update(id, track)
                .map(t -> ResponseEntity.ok(t))
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
