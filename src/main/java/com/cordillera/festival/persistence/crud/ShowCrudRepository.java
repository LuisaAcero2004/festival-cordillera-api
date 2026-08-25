package com.cordillera.festival.persistence.crud;

import com.cordillera.festival.persistence.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ShowCrudRepository extends JpaRepository<ShowEntity,Long> {

    List<ShowEntity> findByArtistId(UUID artistId);
    List<ShowEntity> findByStageIdAndEndDatetimeGreaterThanOrderByStartDatetime(UUID stageId, LocalDateTime currentDatetime);
    List<ShowEntity> findAllByOrderByStartDatetimeAsc();
    List<ShowEntity> findByEndDatetimeGreaterThanOrderByStartDatetime(LocalDateTime currentDatetime);

}
