package com.pr.project_backend.service;

import com.pr.project_backend.data.BitkiEntity;
import com.pr.project_backend.repository.BitkiRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BitkiService {

    private final BitkiRepo repo;

    public List<BitkiEntity> getAllBitkiList() {

        return repo.findAll();
    }

    public List<BitkiEntity> getBitkiList(BitkiEntity.Kind kind) {

        return repo.findByKind(kind);
    }

    public BitkiEntity saveBitki(BitkiEntity bitkiEntity) {

        if (bitkiEntity.getKind() == null) {

            throw new IllegalArgumentException("bitki türü bulunamadı!");
        }

        return repo.save(bitkiEntity);
    }

    public BitkiEntity updateBitki(Long id, BitkiEntity entity) {

        BitkiEntity bitkiEntity = repo.findById(id).orElseThrow(() -> new RuntimeException("bitki seçilemedi !"));

        bitkiEntity.setName(entity.getName());
        bitkiEntity.setDescription(entity.getDescription());
        bitkiEntity.setKind(entity.getKind());
        bitkiEntity.setMax_temp(entity.getMax_temp());
        bitkiEntity.setMin_temp(entity.getMin_temp());

        return repo.save(bitkiEntity);
    }

    public Optional<BitkiEntity> findByIdBitki(Long id){
        return repo.findById(id);
    }

    public void deleteBitki(Long id){

        BitkiEntity entity = repo.findById(id).orElseThrow(()-> new RuntimeException("bitki bulunamadı!" +id));

        repo.deleteById(entity.getId());
    }

}
