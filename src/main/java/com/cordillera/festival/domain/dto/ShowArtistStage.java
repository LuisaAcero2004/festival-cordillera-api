package com.cordillera.festival.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;
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
    @Schema(description = "Show start datetime", example = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startDatetime;

    @NotNull
    @Schema(description = "Show end datetime", example = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime endDatetime;

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

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Boolean getLive() {
        LocalDateTime currentDatetime = LocalDateTime.now(ZoneId.of("America/Bogota"));
        if (startDatetime == null || endDatetime == null) {
            return false;
        }
        return !currentDatetime.isBefore(startDatetime) && !currentDatetime.isAfter(endDatetime);
    }

}
