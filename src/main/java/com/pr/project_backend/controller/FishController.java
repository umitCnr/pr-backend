package com.pr.project_backend.controller;

import com.pr.project_backend.Dto.AkvaryumDto;
import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fish")
@RequiredArgsConstructor
public class FishController {

    private final FishService service;

    @GetMapping("/all")
    public ResponseEntity<List<AkvaryumDto>> getAllFish() {
        List<AkvaryumDto> fishDtos = service.getFishAll();
        return ResponseEntity.ok(fishDtos);
    }

    @GetMapping("/kind/{kind}")
    public ResponseEntity<List<FishEntity>> getToFishKind(@PathVariable FishEntity.Kind kind) {

        List<FishEntity> entities = service.getKindToFish(kind);
        return entities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(entities);
    }

    @PostMapping("/save")
    public ResponseEntity<FishEntity> saveFish(@RequestParam("file") MultipartFile file,
                                               @RequestParam("name") String name,
                                               @RequestParam("kind") FishEntity.Kind kind,
                                               @RequestParam("description") String description) {
        FishEntity fishEntity = new FishEntity();
        fishEntity.setName(name);
        fishEntity.setKind(kind);
        fishEntity.setDescription(description);

        try {
            FishEntity savedFish = service.saveFish(file, fishEntity);
            return ResponseEntity.ok(savedFish); // Başarılı kayıtta 200 OK yanıtı döner
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Hatalı girişlerde 400 Bad Request döner
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Yükleme hatalarında 500 Internal Server Error döner
        }
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


