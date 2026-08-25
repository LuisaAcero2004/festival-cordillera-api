package com.cordillera.festival.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Schema(description = "Artist that will be performing in the festival")
public class Artist {

    @Null
    @Schema(description = "Artist id", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 250)
    @Schema(description = "Artist name", example = "Cultura Profética", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Schema(description = "Main genre", example = "Reggae", requiredMode = Schema.RequiredMode.REQUIRED)
    private String genre;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(description = "Country", example = "Puerto Rico", requiredMode = Schema.RequiredMode.REQUIRED)
    private String country;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
