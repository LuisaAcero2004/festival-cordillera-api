package com.cordillera.festival.web.controller;

import com.cordillera.festival.domain.dto.Artist;
import com.cordillera.festival.domain.service.ArtistService;
import com.cordillera.festival.web.exception.ErrorResponse;
import com.cordillera.festival.web.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.cordillera.festival.web.exception.ErrorMessages.ARTIST_NOT_FOUND;

@RestController
@RequestMapping("/artists")
@Tag(name = "Artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping
    @Operation(summary = "Get the list of artists", description = "Get all the artists or filter by name, genre o country")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of artists")
    public ResponseEntity<List<Artist>> getAll(
            @Parameter(description = "Filter by artist name") @RequestParam(required = false) String name,
            @Parameter(description = "Filter by musical genre") @RequestParam(required = false) String genre,
            @Parameter(description = "Filter by country of origin") @RequestParam(required = false) String country
    ){
        if(name != null && !name.isBlank()){
            return ResponseEntity.ok(artistService.getByName(name));
        }

        if(genre != null && !genre.isBlank()){
            return ResponseEntity.ok(artistService.getByGenre(genre));
        }

        if(country != null && !country.isBlank()){
            return ResponseEntity.ok(artistService.getByCountry(country));
        }

        return ResponseEntity.ok(artistService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Get artist by id", summary = "Returns details for a specific artist by their UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist found"),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Artist> getById(@Parameter(description = "Artist UUID", required = true) @PathVariable("id") UUID id){
        return artistService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ARTIST_NOT_FOUND.format(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new artist", description = "Registers a new artist in the festival database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artist created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Artist> create(@Valid @RequestBody Artist artist){
        return new ResponseEntity<>(artistService.create(artist),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing artist", description = "Update an existing artist in the festival database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artist created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Artist> update(@Valid @RequestBody Artist artist,
                                         @Parameter(description = "Artist UUID", required = true) @PathVariable("id") UUID id){
        artist.setId(id);
        return artistService.update(artist)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ARTIST_NOT_FOUND.format(id)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing artist", description = "Delete an existing artist in the festival database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artist created successfully"),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Artist UUID", required = true) @PathVariable("id")UUID id){
        Boolean deleted = artistService.delete(id);
        if(!deleted){
            throw new ResourceNotFoundException(ARTIST_NOT_FOUND.format(id));
        }
        return ResponseEntity.noContent().build();
    }
}