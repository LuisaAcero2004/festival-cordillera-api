package com.cordillera.festival.persistence.mapper;

import com.cordillera.festival.domain.dto.Stage;
import com.cordillera.festival.persistence.entity.StageEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StageMapper {

    Stage toStage(StageEntity stageEntity);

    List<Stage> toStages(List<StageEntity> stageEntities);

    @InheritInverseConfiguration
    StageEntity toStageEntity(Stage stage);

}
