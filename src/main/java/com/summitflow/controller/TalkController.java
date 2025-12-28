package com.summitflow.controller;

import com.summitflow.controller.request.TalkRequest;
import com.summitflow.controller.response.TalkResponse;
import com.summitflow.entity.Talk;
import com.summitflow.exception.ResourceNotFoundException;
import com.summitflow.mapper.TalkMapper;
import com.summitflow.service.TalkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/talks")
@RequiredArgsConstructor
@Tag(name = "Talks", description = "Endpoints for managing conference talks")
@SecurityRequirement(name = "bearerAuth")
public class TalkController {

    private final TalkService talkService;

    @GetMapping
    @Operation(summary = "List all talks", description = "Returns a complete list of all registered talks.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<TalkResponse>> getAll(){
        List<TalkResponse> talks = talkService.findAll()
                .stream()
                .map(talk -> TalkMapper.toResponse(talk))
                .toList();

        return ResponseEntity.ok(talks);
    }

    @PostMapping
    @Operation(
            summary = "Create a new talk",
            description = "Registers a new talk. It validates if the provided Speaker IDs and Track IDs exist in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Talk created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)"),
            @ApiResponse(responseCode = "404", description = "One or more Speaker/Track IDs not found")
    })
    public ResponseEntity<TalkResponse> save(@Valid @RequestBody TalkRequest request){
        Talk savedTalk = talkService.save(TalkMapper.toTalk(request));
        return ResponseEntity.ok(TalkMapper.toResponse(savedTalk));
    }

    @Operation(summary = "Get a talk by ID", description = "Returns a specific talk by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Talk found"),
            @ApiResponse(responseCode = "404", description = "Talk not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TalkResponse> findById(
            @Parameter(description = "ID of the talk to be retrieved", example = "1")
            @PathVariable Long id){
        return talkService.findById(id)
                .map(talk -> ResponseEntity.ok(TalkMapper.toResponse(talk)))
                .orElseThrow(() -> new ResourceNotFoundException("Talk not found"));
    }

    @Operation(
            summary = "Update a talk",
            description = "Updates an existing talk by ID. Note: The lists of Speakers and Tracks are fully replaced by the new values provided."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Talk updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found. Causes:\n" +
                            "1. The Talk ID does not exist.\n" +
                            "2. One or more provided Speaker IDs or Track IDs do not exist."
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TalkResponse> update(
            @Parameter(description = "ID of the talk to be updated", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TalkRequest request){
        return talkService.update(id, TalkMapper.toTalk(request))
                .map(talk -> ResponseEntity.ok(TalkMapper.toResponse(talk)))
                .orElseThrow(() -> new ResourceNotFoundException("Talk not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a talk", description = "Removes a talk from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Talk deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Talk not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the talk to be deleted", example = "1")
            @PathVariable Long id){
        Optional<Talk> talk = talkService.findById(id);
        if (talk.isPresent()){
            talkService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Talk not found");
        }
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search talks by tracks",
            description = "Filters talks that belong to at least one of the provided track IDs."
    )
    @ApiResponse(responseCode = "200", description = "Search completed successfully")
    public ResponseEntity<List<TalkResponse>> findByTracks(
            @Parameter(description = "List of Track IDs to filter by", example = "1,2")
            @RequestParam List<Long> trackIds){
        List<TalkResponse> tracks = talkService.findByTracks(trackIds)
                .stream()
                .map(track -> TalkMapper.toResponse(track))
                .toList();

        return ResponseEntity.ok(tracks);
    }

}
