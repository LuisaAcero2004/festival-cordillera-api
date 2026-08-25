package com.cordillera.festival.web.controller;

import com.cordillera.festival.domain.dto.Stage;
import com.cordillera.festival.domain.service.StageService;
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

import static com.cordillera.festival.web.exception.ErrorMessages.STAGE_NOT_FOUND;

@RestController
@RequestMapping("/stages")
@Tag(name = "Stages")
public class StageController {

    private final StageService stageService;

    public StageController(StageService stageService){
        this.stageService = stageService;
    }

    @GetMapping("/active")
    @Operation(summary = "Get the list of active stages", description = "Get stages filtered by active=true")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of active stages")
    public ResponseEntity<List<Stage>> getActive(){
        return ResponseEntity.ok(stageService.getActive());
    }

    @GetMapping
    @Operation(summary = "Get the list of stages", description = "Get all the stages")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of all stages")
    public ResponseEntity<List<Stage>> getAll(){
        return ResponseEntity.ok(stageService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get stage by id", description = "Returns details for a specific stage by their UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the stage by given id"),
            @ApiResponse(responseCode = "404", description = "Stage not found by given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Stage> getById(@Parameter(description = "Stage UUID",required = true) @PathVariable("id") UUID id){
        return stageService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(STAGE_NOT_FOUND.format(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new stage", description = "Registers a new stage in the festival database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stage successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Stage> create(@Valid @RequestBody Stage stage){
        return new ResponseEntity<>(stageService.create(stage),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing stage", description = "Update an existing stage in the festival database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the stage by given id"),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Stage not found by given id"
                    , content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Stage> update(@Valid @RequestBody Stage stage,
                                        @Parameter(description = "Stage UUID", required = true) @PathVariable("id") UUID id){
        stage.setId(id);
        return stageService.update(stage)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(STAGE_NOT_FOUND.format(stage.getId())));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing stage by id", description = "Delete existing stage in the festival database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successfully deleted a stage"),
            @ApiResponse(responseCode = "404", description = "Stage not found by given id"
                    , content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Stage UUID", required = true) @PathVariable("id") UUID id){
        Boolean deleted = stageService.delete(id);
        if(!deleted){
                throw new ResourceNotFoundException(STAGE_NOT_FOUND.format(id));
        }
        return ResponseEntity.noContent().build();
    }

}
