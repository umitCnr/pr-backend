package com.pr.project_backend.controller;


import com.pr.project_backend.Dto.OmurgasizDto;
import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.data.OmurgasizEntity;
import com.pr.project_backend.service.OmurgasizService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/omurgasiz")
@RequiredArgsConstructor
public class OmurgasizController {

    private final OmurgasizService service;

    @GetMapping("/getAll")
    private ResponseEntity<List<OmurgasizDto>> getAllOmurgasiz() {

        List<OmurgasizDto> dtos = service.getOmurgasizAll();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/getKind/{kind}")
    private ResponseEntity<List<OmurgasizDto>> getByKind(@PathVariable OmurgasizEntity.Kind kind) {
        List<OmurgasizDto> entities = service.getKindToOmurgasiz(kind);

        return entities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entities);
    }

    @PostMapping("/save")
    private ResponseEntity<OmurgasizEntity> saveOmurgasiz(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("kind") OmurgasizEntity.Kind kind,
                                                          @RequestParam("description") String description) {

        OmurgasizEntity entity = new OmurgasizEntity();
        entity.setName(name);
        entity.setKind(kind);
        entity.setDescription(description);

        try {
            OmurgasizEntity omurgasizEntity = service.saveOmurgasiz(entity, file);
            return ResponseEntity.ok(omurgasizEntity);
        } catch (Exception e) {
            System.out.println("yüklenemedi");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<OmurgasizEntity> deleteFish(@PathVariable Long id) {

        service.deleteFish(id);
        return ResponseEntity.noContent().build();
    }
}
