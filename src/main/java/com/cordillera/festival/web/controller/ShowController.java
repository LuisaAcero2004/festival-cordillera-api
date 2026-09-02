package com.cordillera.festival.web.controller;

import com.cordillera.festival.domain.dto.ShowArtistStage;
import com.cordillera.festival.domain.service.ShowService;
import com.cordillera.festival.web.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.cordillera.festival.web.exception.ErrorMessages.SHOW_NOT_FOUND;

@RestController
@RequestMapping("/shows")
@Tag(name = "Shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @GetMapping
    @Operation(summary = "Get the list of shows",
            description = "Get all the shows existing on the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of shows")
    public ResponseEntity<List<ShowArtistStage>> getAll(){
        return ResponseEntity.ok(showService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get show by id",
            description = "Returns details for a specific show by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve the show by given id"),
            @ApiResponse(responseCode = "404", description = "Show not found by given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ShowArtistStage> getById(@Parameter(description = "Show id", required = true)
                                                       @PathVariable("id") Long id){
        return showService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(SHOW_NOT_FOUND.format(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new show",
            description = "Registers a new show in the festival database",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Show successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid payload",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ShowArtistStage> create(@Valid @RequestBody ShowArtistStage show){
        return new ResponseEntity<>(showService.create(show), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing show",
            description = "Update a existing show in the festival database",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Show successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid payload",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Show not found by given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ShowArtistStage> update(@Parameter(description = "Show id", required = true)
                                                      @PathVariable("id") Long id,
                                                  @Valid @RequestBody ShowArtistStage show){
        show.setId(id);
        return showService.update(show)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException(SHOW_NOT_FOUND.format(id)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing show",
            description = "Delete an existing show in the festival database.",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Show deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Show not found",
                    content = @Content(schema = @Schema(implementation = com.cordillera.festival.web.exception.ErrorResponse.class)))
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Show id", required = true)
                                           @PathVariable("id") Long id){
        Boolean deleted = showService.delete(id);
        if(!deleted){
            throw new ResourceNotFoundException(SHOW_NOT_FOUND.format(id));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/artists/{id}")
    @Operation(summary = "Get shows by artist id",
            description = "Returns list of shows by artist id")
    @ApiResponse(responseCode = "200", description = "Successfully retrieve the shows by given artist id")
    public ResponseEntity<List<ShowArtistStage>> getByArtistId(@Parameter(description = "Artist id", required = true)
                                                                   @PathVariable("id") UUID artistId){
        return ResponseEntity.ok(showService.getByArtist(artistId));
    }

    @GetMapping("/sorted")
    @Operation(summary = "Get the list of shows sorted",
            description = "Get the list of shows sorted by datetime")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of shows")
    public ResponseEntity<List<ShowArtistStage>> getAllSorted(){
        return ResponseEntity.ok(showService.getAllSorted());
    }

    @GetMapping("/upcoming/stages/{id}")
    @Operation(summary = "Get upcoming shows by stage id",
            description = "Returns list of upcoming shows by stage id")
    @ApiResponse(responseCode = "200",
            description = "Successfully retrieve the upcoming shows by the given stage id")
    public ResponseEntity<List<ShowArtistStage>> getUpcomingByStage(@Parameter(description = "Stage id", required = true)
                                                                        @PathVariable("id") UUID stageId){
        return ResponseEntity.ok(showService.getUpcomingByStage(stageId));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Get the list of upcoming shows",
            description = "Get the list of upcoming shows sorted by start datetime")
    @ApiResponse(responseCode = "200",
            description = "Successfully retrieved list of shows")
    public ResponseEntity<List<ShowArtistStage>> getUpcoming(){
        return ResponseEntity.ok(showService.getUpcoming());
    }



}
