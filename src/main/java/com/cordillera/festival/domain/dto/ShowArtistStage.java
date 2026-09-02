package com.cordillera.festival.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Schema(description = "Shows that will be part of the festival")
public class ShowArtistStage {

    @Null
    @Schema(description = "Show id", example = "5",  accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(description = "Show artists", implementation = Artist.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private Artist artist;

    @NotNull
    @Schema(description = "Show stage", implementation = Stage.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private Stage stage;

    @NotNull
    @Schema(description = "Show start datetime", example = "2026-09-12T18:30:00-05:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime startDatetime;

    @NotNull
    @Schema(description = "Show end datetime", example = "2026-09-12T18:30:00-05:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime endDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public OffsetDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(OffsetDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public OffsetDateTime getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(OffsetDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

    public boolean getLive() {
        if (startDatetime == null || endDatetime == null) {
            return false;
        }

        OffsetDateTime now =
                OffsetDateTime.now(ZoneId.of("America/Bogota"));

        return !now.isBefore(startDatetime)
                && !now.isAfter(endDatetime);
    }

}
