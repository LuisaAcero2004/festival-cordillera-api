package com.cordillera.festival.persistence.crud;

import com.cordillera.festival.persistence.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArtistCrudRepository extends JpaRepository<ArtistEntity, UUID> {

    List<ArtistEntity> findByName(String name);
    List<ArtistEntity> findByGenreOrderByNameAsc(String genre);
    List<ArtistEntity> findByCountryOrderByNameAsc(String country);

}
