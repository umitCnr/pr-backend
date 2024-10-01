package com.pr.project_backend.controller;

import com.google.api.Http;
import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fish")
@RequiredArgsConstructor
public class FishController {

    private final FishService service;

    @GetMapping("/kind")
    public ResponseEntity<List<FishEntity>> getAllFish() {

        List<FishEntity> entities = service.getFishAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/kind/{kind}")
    public ResponseEntity<List<FishEntity>> getToFishKind(@PathVariable FishEntity.Kind kind) {

        List<FishEntity> entities = service.getKindToFish(kind);
        return entities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(entities);
    }

    @PostMapping("/save")
    public ResponseEntity<FishEntity> saveFish(@RequestParam FishEntity fishEntity) {

        FishEntity entities = service.saveFish(fishEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entities);
    }

    @PutMapping("/kind/{id}")
    public ResponseEntity<FishEntity> uploadFish(@PathVariable Long id, FishEntity fishEntity) {

        FishEntity entity = service.updateFish(id, fishEntity);
        return ResponseEntity.ok(entity);

    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<FishEntity> deleteFish(@PathVariable Long id) {

        service.deleteFish(id);
        return ResponseEntity.noContent().build();//204 durumu
    }

}


