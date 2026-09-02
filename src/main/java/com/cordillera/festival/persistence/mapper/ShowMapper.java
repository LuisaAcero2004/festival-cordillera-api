package com.cordillera.festival.persistence.mapper;

import com.cordillera.festival.domain.dto.ShowArtistStage;
import com.cordillera.festival.persistence.entity.ShowEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, StageMapper.class})
public interface ShowMapper {

    ZoneId FESTIVAL_ZONE = ZoneId.of("America/Bogota");

    default OffsetDateTime map(LocalDateTime value) {
        return value == null
                ? null
                : value.atZone(FESTIVAL_ZONE).toOffsetDateTime();
    }

    default LocalDateTime map(OffsetDateTime value) {
        return value == null
                ? null
                : value.atZoneSameInstant(FESTIVAL_ZONE).toLocalDateTime();
    }

    ShowArtistStage toShowArtistStage(ShowEntity showEntity);

    List<ShowArtistStage> toShowsArtistStage(List<ShowEntity> showEntities);

    @InheritInverseConfiguration
    ShowEntity toShowEntity(ShowArtistStage showArtistStage);

}
