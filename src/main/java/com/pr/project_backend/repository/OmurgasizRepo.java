package com.pr.project_backend.repository;

import com.pr.project_backend.data.OmurgasizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmurgasizRepo extends JpaRepository<OmurgasizEntity,Long> {


    List<OmurgasizEntity> findByKind(OmurgasizEntity.Kind kind);
}
