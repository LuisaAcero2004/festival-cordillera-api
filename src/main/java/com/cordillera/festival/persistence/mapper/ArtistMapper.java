package com.cordillera.festival.persistence.mapper;

import com.cordillera.festival.domain.dto.Artist;
import com.cordillera.festival.persistence.entity.ArtistEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    Artist toArtist(ArtistEntity artistEntity);

    List<Artist> toArtists(List<ArtistEntity> artistEntities);

    @InheritInverseConfiguration
    ArtistEntity toArtistEntity(Artist artist);

}
