package com.summitflow.controller;

import com.summitflow.controller.request.TrackRequest;
import com.summitflow.controller.response.TrackResponse;
import com.summitflow.entity.Track;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.mapper.TrackMapper;
import com.summitflow.service.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
@Tag(name = "Tracks", description = "Endpoints for managing conference tracks (categories)")
@SecurityRequirement(name = "bearerAuth")
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    @Operation(summary = "List all tracks", description = "Returns a complete list of all knowledge tracks.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<TrackResponse>> getAll(){
        List<TrackResponse> tracks = trackService.findAll().stream()
                .map(track -> TrackMapper.toResponse(track))
                .toList();

        return ResponseEntity.ok().body(tracks);
    }

    @Operation(summary = "Get a track by ID", description = "Returns a specific track by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Track found"),
            @ApiResponse(responseCode = "404", description = "Track not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrackResponse> getById(
            @Parameter(description = "ID of the track to be retrieved", example = "1")
            @PathVariable Long id){
        return trackService.findById(id)
                .map(track -> ResponseEntity.ok(TrackMapper.toResponse(track)))
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new track", description = "Registers a new knowledge track (category).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Track created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TrackResponse> save(@Valid @RequestBody TrackRequest request){
        Track savedTrack = trackService.save(TrackMapper.toTrack(request));
        return ResponseEntity.ok(TrackMapper.toResponse(savedTrack));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a track", description = "Updates an existing track by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Track updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Track not found")
    })
    public ResponseEntity<TrackResponse> update(
            @Parameter(description = "ID of the track to be updated", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TrackRequest request){
        return trackService.update(id, TrackMapper.toTrack(request))
                .map(t -> ResponseEntity.ok(TrackMapper.toResponse(t)))
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a track", description = "Removes a track from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Track deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Track not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Track> track = trackService.findById(id);
        if (track.isPresent()){
            trackService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Track not found");
        }
    }

}
