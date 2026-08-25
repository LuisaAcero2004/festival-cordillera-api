package com.cordillera.festival.domain.service;

import com.cordillera.festival.domain.dto.ShowArtistStage;
import com.cordillera.festival.domain.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository){
        this.showRepository = showRepository;
    }

    public List<ShowArtistStage> getAll(){
        return showRepository.getAll();
    }

    public Optional<ShowArtistStage> getById(Long id){
        return showRepository.getById(id);
    }

    public ShowArtistStage create(ShowArtistStage show){
        return showRepository.save(show);
    }

    public Optional<ShowArtistStage> update(ShowArtistStage show){
        return showRepository.getById(show.getId())
                .map(presentShow ->{
                    return showRepository.save(show);
                });
    }

    public Boolean delete(Long id){
        if(showRepository.getById(id).isPresent()){
            showRepository.delete(id);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public List<ShowArtistStage> getByArtist(UUID artistId){
        return showRepository.getByArtistId(artistId);
    }

    public List<ShowArtistStage> getAllSorted(){
        return showRepository.getAllSortByStartDatetime();
    }

    public List<ShowArtistStage> getUpcomingByStage(UUID stageId){
        return showRepository.getUpcomingByStageIdSortByStartDatetime(stageId);
    }

    public List<ShowArtistStage> getUpcoming(){
        return showRepository.getUpcomingSortByStartDatetime();
    }

}
