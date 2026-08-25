package com.cordillera.festival.domain.service;

import com.cordillera.festival.domain.dto.Artist;
import com.cordillera.festival.domain.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAll(){
        return artistRepository.getAll();
    }

    public Optional<Artist> getById(UUID id){
        return artistRepository.getById(id);
    }

    public List<Artist> getByName(String name){
        return artistRepository.getByName(name);
    }

    public List<Artist> getByGenre(String genre){
        return artistRepository.getByGenreSortByName(genre);
    }

    public List<Artist> getByCountry(String country){
        return artistRepository.getByCountrySortByName(country);
    }

    public Artist create(Artist artist){
        return artistRepository.save(artist);
    }

    public Optional<Artist> update(Artist artist){
        return artistRepository.getById(artist.getId())
                .map(presentArtist -> {
                    return artistRepository.save(artist);
                });
    }

    public Boolean delete(UUID id){
        if (artistRepository.getById(id).isPresent()){
            artistRepository.delete(id);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

}
