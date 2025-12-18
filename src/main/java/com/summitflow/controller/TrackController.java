package com.summitflow.controller;

import com.summitflow.entity.Track;
import com.summitflow.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public List<Track> getAll(){
        return trackService.findAll();
    }

    @GetMapping("/{id}")
    public Track getById(@PathVariable Long id){
        return trackService.findById(id);
    }

    @PostMapping
    public Track save(@RequestBody Track track){
        return trackService.save(track);
    }

    @PutMapping("/{id}")
    public Track update(@PathVariable Long id, @RequestBody Track track){
        return trackService.update(id, track);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){
        trackService.deleteById(id);
    }

}
