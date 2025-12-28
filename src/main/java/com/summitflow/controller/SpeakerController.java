package com.summitflow.controller;

import com.summitflow.controller.request.SpeakerRequest;
import com.summitflow.controller.response.SpeakerResponse;
import com.summitflow.entity.Speaker;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.mapper.SpeakerMapper;
import com.summitflow.service.SpeakerService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/speakers")
@Tag(name = "Speakers", description = "Endpoints for managing conference speakers")
@SecurityRequirement(name = "bearerAuth")
public class SpeakerController {

    private final SpeakerService speakerService;

    @GetMapping
    @Operation(summary = "List all speakers", description = "Returns a complete list of all registered speakers.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<SpeakerResponse>> getAll(){
        List<SpeakerResponse> speakers = speakerService.findAll()
                .stream()
                .map(speaker -> SpeakerMapper.toResponse(speaker))
                .toList();

        return ResponseEntity.ok(speakers);
    }

    @PostMapping
    @Operation(summary = "Register a new speaker", description = "Creates a new speaker profile in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speaker created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)")
    })
    public ResponseEntity<SpeakerResponse> save(@Valid @RequestBody SpeakerRequest request){
        Speaker savedSpeaker = speakerService.save(SpeakerMapper.toSpeaker(request));
        return ResponseEntity.ok(SpeakerMapper.toResponse(savedSpeaker));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a speaker by ID", description = "Returns a specific speaker profile by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speaker found"),
            @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    public ResponseEntity<SpeakerResponse> getById(
            @Parameter(description = "ID of the speaker to be retrieved", example = "1")
            @PathVariable Long id){
        return speakerService.findById(id)
                .map(speaker -> ResponseEntity.ok(SpeakerMapper.toResponse(speaker)))
                .orElseThrow(() -> new ResourceNotFoundException("Speaker not found"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a speaker", description = "Updates an existing speaker profile by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speaker updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    public ResponseEntity<SpeakerResponse> update(
            @Parameter(description = "ID of the speaker to be updated", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody SpeakerRequest request){
        return speakerService.update(id, SpeakerMapper.toSpeaker(request))
                .map(speaker -> ResponseEntity.ok(SpeakerMapper.toResponse(speaker)))
                .orElseThrow(() -> new ResourceNotFoundException("Speaker not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a speaker", description = "Removes a speaker profile from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Speaker deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the speaker to be deleted", example = "1")
            @PathVariable Long id){
        Optional<Speaker> speaker = speakerService.findById(id);
        if (speaker.isPresent()){
            speakerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Speaker not found");
        }
    }

}
