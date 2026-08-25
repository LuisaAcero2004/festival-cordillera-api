package com.cordillera.festival.persistence;

import com.cordillera.festival.domain.dto.ShowArtistStage;
import com.cordillera.festival.domain.repository.ShowRepository;
import com.cordillera.festival.persistence.crud.ShowCrudRepository;
import com.cordillera.festival.persistence.entity.ShowEntity;
import com.cordillera.festival.persistence.mapper.ShowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShowRepositoryImpl implements ShowRepository {

    private final ShowCrudRepository showCrudRepository;
    private final ShowMapper mapper;

    public ShowRepositoryImpl(ShowCrudRepository showCrudRepository, ShowMapper mapper){
        this.showCrudRepository = showCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ShowArtistStage> getAll() {
        return mapper.toShowsArtistStage(showCrudRepository.findAll());
    }

    @Override
    public Optional<ShowArtistStage> getById(Long id) {
        return showCrudRepository.findById(id)
                .map(mapper::toShowArtistStage);
    }

    @Override
    public ShowArtistStage save(ShowArtistStage showArtistStage) {
        ShowEntity showEntity = mapper.toShowEntity(showArtistStage);
        ShowEntity savedShowEntity = showCrudRepository.save(showEntity);
        return mapper.toShowArtistStage(savedShowEntity);
    }

    @Override
    public void delete(Long id) {
        showCrudRepository.deleteById(id);
    }

    @Override
    public List<ShowArtistStage> getByArtistId(UUID artistId) {
        return mapper.toShowsArtistStage(showCrudRepository.findByArtistId(artistId));
    }

    @Override
    public List<ShowArtistStage> getAllSortByStartDatetime() {
        return mapper.toShowsArtistStage(showCrudRepository.findAllByOrderByStartDatetimeAsc());
    }

    @Override
    public List<ShowArtistStage> getUpcomingByStageIdSortByStartDatetime(UUID stageId) {
        LocalDateTime currentDatetime = LocalDateTime.now(ZoneId.of("America/Bogota"));
        return mapper.toShowsArtistStage(showCrudRepository.findByStageIdAndEndDatetimeGreaterThanOrderByStartDatetime(stageId,currentDatetime));
    }

    @Override
    public List<ShowArtistStage> getUpcomingSortByStartDatetime() {
        LocalDateTime currentDatetime = LocalDateTime.now(ZoneId.of("America/Bogota"));
        return mapper.toShowsArtistStage(showCrudRepository.findByEndDatetimeGreaterThanOrderByStartDatetime(currentDatetime));
    }
}
