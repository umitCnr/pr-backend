package com.pr.project_backend.repository;

import com.pr.project_backend.data.BitkiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitkiRepo extends JpaRepository<BitkiEntity,Long> {


    List<BitkiEntity> findByKind(BitkiEntity.Kind kind);
}
