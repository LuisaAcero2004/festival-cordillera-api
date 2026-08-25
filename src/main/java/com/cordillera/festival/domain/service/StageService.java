package com.cordillera.festival.domain.service;

import com.cordillera.festival.domain.dto.Artist;
import com.cordillera.festival.domain.dto.Stage;
import com.cordillera.festival.domain.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StageService {

    private final StageRepository stageRepository;

    public StageService(StageRepository stageRepository){
        this.stageRepository = stageRepository;
    }

    public List<Stage> getActive(){
        return stageRepository.getActive();
    }

    public List<Stage> getAll(){
        return stageRepository.getAll();
    }

    public Optional<Stage> getById(UUID id){
        return stageRepository.getById(id);
    }

    public Stage create(Stage stage){
        return stageRepository.save(stage);
    }

    public Optional<Stage> update(Stage stage){
        return stageRepository.getById(stage.getId())
                .map(presentStage -> {
                    return stageRepository.save(stage);
                });
    }

    public Boolean delete(UUID id){
        if(stageRepository.getById(id).isPresent()){
            stageRepository.delete(id);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

}
