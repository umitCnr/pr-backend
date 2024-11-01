package com.pr.project_backend.service;

import com.pr.project_backend.data.OmurgasizEntity;
import com.pr.project_backend.repository.OmurgasizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OmurgasizService {

    private final OmurgasizRepo repo;

    public List<OmurgasizEntity> getAllOmurgasiz() {

        return repo.findAll();
    }

    public List<OmurgasizEntity> getByKind(OmurgasizEntity.Kind kind) {

        return repo.findByKind(kind);
    }

    public OmurgasizEntity saveOmurgasiz(OmurgasizEntity entity) {

        return repo.save(entity);
    }

    public Optional<OmurgasizEntity> findByOptianal(Long id) {

        return repo.findById(id);
    }

    public OmurgasizEntity updateOmurgasiz(Long id, OmurgasizEntity omurgasizEntity) {

        OmurgasizEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("Omurgasiz Canlı seçilmedi"));

        entity.setName(omurgasizEntity.getName());
        entity.setDescription(omurgasizEntity.getDescription());
        entity.setKind(omurgasizEntity.getKind());

        return repo.save(entity);
    }

    public void deleteOmurgasiz(Long id){
        repo.deleteById(id);
    }

}
