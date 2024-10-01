package com.pr.project_backend.service;

import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.repository.FishRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {

    private final FishRepo repo;

    public List<FishEntity> getFishAll() {
        return repo.findAll();
    }

    public List<FishEntity> getKindToFish(FishEntity.Kind kind){
        return repo.findByKind(kind);
    }

    public FishEntity saveFish(FishEntity fishEntity) {

        if (fishEntity.getKind() == null) {
            throw new IllegalArgumentException("tür bulunamadı");
        }
        return repo.save(fishEntity);
    }


    public FishEntity updateFish(Long id, FishEntity fishEntity) {

        FishEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("güncellenecek balık bulunamadı!" + id));

        entity.setName(fishEntity.getName());
        entity.setKind(fishEntity.getKind());
        entity.setDescription(fishEntity.getDescription());
        entity.setUrl(fishEntity.getUrl());

        return repo.save(entity);
    }

    public void deleteFish(Long id){

        FishEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("balık bulunamadı!" +id));
        repo.delete(entity);
    }

}
