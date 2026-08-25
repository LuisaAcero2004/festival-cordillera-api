package com.cordillera.festival.persistence;

import com.cordillera.festival.domain.dto.Artist;
import com.cordillera.festival.domain.repository.ArtistRepository;
import com.cordillera.festival.persistence.crud.ArtistCrudRepository;
import com.cordillera.festival.persistence.entity.ArtistEntity;
import com.cordillera.festival.persistence.mapper.ArtistMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

    private final ArtistCrudRepository artistCrudRepository;
    private final ArtistMapper mapper;

    public ArtistRepositoryImpl(ArtistCrudRepository artistCrudRepository, ArtistMapper mapper){
        this.artistCrudRepository = artistCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Artist> getAll() {
        return mapper.toArtists(artistCrudRepository.findAll());
    }

    @Override
    public Optional<Artist> getById(UUID id) {
        return artistCrudRepository.findById(id)
                .map(mapper::toArtist);
    }

    @Override
    public List<Artist> getByName(String name) {
        return mapper.toArtists(artistCrudRepository.findByName(name));
    }

    @Override
    public List<Artist> getByGenreSortByName(String genre) {
        return mapper.toArtists(artistCrudRepository.findByGenreOrderByNameAsc(genre));
    }

    @Override
    public List<Artist> getByCountrySortByName(String country) {
        return mapper.toArtists(artistCrudRepository.findByCountryOrderByNameAsc(country));
    }

    @Override
    public Artist save(Artist artist) {
        ArtistEntity artistEntity = mapper.toArtistEntity(artist);
        ArtistEntity savedArtistEntityEntity = artistCrudRepository.save(artistEntity);
        return mapper.toArtist(savedArtistEntityEntity);
    }

    @Override
    public void delete(UUID id) {
        artistCrudRepository.deleteById(id);
    }
}
