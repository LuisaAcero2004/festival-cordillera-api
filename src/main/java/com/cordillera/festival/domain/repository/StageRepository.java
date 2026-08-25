package com.cordillera.festival.domain.repository;

import com.cordillera.festival.domain.dto.Stage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StageRepository {

    List<Stage> getActive();
    List<Stage> getAll();
    Stage save(Stage stage);
    Optional<Stage> getById(UUID id);
    void delete(UUID id);

}
