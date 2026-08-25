package com.cordillera.festival.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class Stage {

    @Null
    @Schema(description = "Stage id", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(description = "Stage name", example = "Aconcagua", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Size(max = 250)
    @Schema(description = "Stage location", example = "The first stage you'll see when arrive at the festival", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String location;

    @NotNull
    @Schema(description = "Stage active", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
