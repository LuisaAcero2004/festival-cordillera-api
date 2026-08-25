package com.cordillera.festival.persistence.mapper;

import com.cordillera.festival.domain.dto.ShowArtistStage;
import com.cordillera.festival.persistence.entity.ShowEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, StageMapper.class})
public interface ShowMapper {

    ShowArtistStage toShowArtistStage(ShowEntity showEntity);

    List<ShowArtistStage> toShowsArtistStage(List<ShowEntity> showEntities);

    @InheritInverseConfiguration
    ShowEntity toShowEntity(ShowArtistStage showArtistStage);

}
