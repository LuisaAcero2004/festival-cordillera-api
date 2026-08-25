package com.cordillera.festival.persistence.crud;

import com.cordillera.festival.persistence.entity.StageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StageCrudRepository extends JpaRepository<StageEntity, UUID> {

    List<StageEntity> findByIsActiveTrue();

}
