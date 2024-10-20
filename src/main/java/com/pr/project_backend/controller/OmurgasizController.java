package com.pr.project_backend.controller;


import com.pr.project_backend.data.OmurgasizEntity;
import com.pr.project_backend.service.OmurgasizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/omurgasiz")
@RequiredArgsConstructor
public class OmurgasizController {

    private final OmurgasizService service;

    @GetMapping("/getAll")
    private ResponseEntity<List<OmurgasizEntity>> getAllOmurgasiz() {

        List<OmurgasizEntity> entities = service.getAllOmurgasiz();

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/getKind/{kind}")
    private ResponseEntity<List<OmurgasizEntity>> getByKind(@PathVariable OmurgasizEntity.Kind kind) {
        List<OmurgasizEntity> entities = service.getByKind(kind);

        return entities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entities);
    }

    @PostMapping("/save")
    private ResponseEntity<OmurgasizEntity> saveOmurgasiz(@RequestBody OmurgasizEntity omurgasizEntity) {

        OmurgasizEntity entity = service.saveOmurgasiz(omurgasizEntity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<OmurgasizEntity> updateOmurgasiz(@PathVariable Long id, @RequestBody OmurgasizEntity omurgasizEntity) {

        Optional<OmurgasizEntity> entity = service.findByOptianal(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            OmurgasizEntity update = service.updateOmurgasiz(id, omurgasizEntity);
            return ResponseEntity.ok(update);
        }
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteOmurgasiz(@PathVariable Long id) {
        Optional<OmurgasizEntity> entity = service.findByOptianal(id);
        if (entity.isPresent()) {
            service.deleteOmurgasiz(id);
            return ResponseEntity.ok("Omurgasız canlı başarıyla silindi!");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Omurgasız canlı bulunamadı, silinemedi!");
    }
}
