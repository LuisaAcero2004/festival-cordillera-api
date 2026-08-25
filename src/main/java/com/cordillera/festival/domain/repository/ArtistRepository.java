package com.cordillera.festival.domain.repository;

import com.cordillera.festival.domain.dto.Artist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository {

    List<Artist> getAll();
    Optional<Artist> getById(UUID id);
    List<Artist> getByName(String name);
    List<Artist> getByGenreSortByName(String genre);
    List<Artist> getByCountrySortByName(String country);
    Artist save(Artist artist);
    void delete(UUID id);

}
