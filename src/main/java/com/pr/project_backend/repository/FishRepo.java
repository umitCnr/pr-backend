package com.pr.project_backend.repository;

import com.pr.project_backend.data.FishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishRepo extends JpaRepository<FishEntity,Long> {

    List<FishEntity> findByKind(FishEntity.Kind kind);

}
