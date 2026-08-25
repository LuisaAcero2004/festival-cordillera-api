package com.cordillera.festival.domain.repository;

import com.cordillera.festival.domain.dto.ShowArtistStage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowRepository {

    List<ShowArtistStage> getAll();
    Optional<ShowArtistStage> getById(Long id);
    ShowArtistStage save(ShowArtistStage showArtistStage);
    void delete(Long id);
    List<ShowArtistStage> getByArtistId(UUID artistId);
    List<ShowArtistStage> getAllSortByStartDatetime();
    List<ShowArtistStage> getUpcomingByStageIdSortByStartDatetime(UUID stageId);
    List<ShowArtistStage> getUpcomingSortByStartDatetime();

}
