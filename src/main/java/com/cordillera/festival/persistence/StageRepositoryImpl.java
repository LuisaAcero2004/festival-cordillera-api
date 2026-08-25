package com.cordillera.festival.persistence;

import com.cordillera.festival.domain.dto.Stage;
import com.cordillera.festival.domain.repository.StageRepository;
import com.cordillera.festival.persistence.crud.StageCrudRepository;
import com.cordillera.festival.persistence.entity.StageEntity;
import com.cordillera.festival.persistence.mapper.StageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StageRepositoryImpl implements StageRepository {

    private final StageCrudRepository stageCrudRepository;
    private final StageMapper mapper;

    public StageRepositoryImpl(StageCrudRepository stageCrudRepository, StageMapper mapper){
        this.stageCrudRepository = stageCrudRepository;
        this.mapper = mapper;
    }


    @Override
    public List<Stage> getActive() {
        return mapper.toStages(stageCrudRepository.findByIsActiveTrue());
    }

    @Override
    public List<Stage> getAll() {
        return mapper.toStages(stageCrudRepository.findAll());
    }

    @Override
    public Stage save(Stage stage) {
        StageEntity stageEntity = mapper.toStageEntity(stage);
        StageEntity savedStageEntity = stageCrudRepository.save(stageEntity);
        return mapper.toStage(savedStageEntity);
    }

    @Override
    public Optional<Stage> getById(UUID id) {
        return stageCrudRepository.findById(id).map(mapper::toStage);
    }

    @Override
    public void delete(UUID id) {
        stageCrudRepository.deleteById(id);
    }
}
