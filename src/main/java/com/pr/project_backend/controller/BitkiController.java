package com.pr.project_backend.controller;

import com.pr.project_backend.Dto.BitkiDto;
import com.pr.project_backend.data.BitkiEntity;
import com.pr.project_backend.service.BitkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bitki")
public class BitkiController {

    private final BitkiService service;

    @GetMapping("/getKind/{kind}")
    private ResponseEntity<List<BitkiDto>> getKindBitki(@PathVariable BitkiEntity.Kind kind) {

        List<BitkiDto> entity = service.getBitkiList(kind);

        return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
    }

    @GetMapping("/getAll")
    private ResponseEntity<List<BitkiDto>> getAllBitki() {

        List<BitkiDto> entities = service.getAllBitkiList();

        return entities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(entities);

    }

    @PostMapping("/save")
    private ResponseEntity<BitkiEntity> saveBitki(@RequestParam("file") MultipartFile file
            , @RequestParam("name") String name
            , @RequestParam("kind") BitkiEntity.Kind kind
            , @RequestParam("description") String description
            , @RequestParam("min") Long min
            , @RequestParam("max") Long max) {

        BitkiEntity entity = new BitkiEntity();

        entity.setName(name);
        entity.setMin_temp(min);
        entity.setMax_temp(max);
        entity.setDescription(description);
        entity.setKind(kind);

        try {
            BitkiEntity entity1 = service.saveBitki(entity, file);
            return ResponseEntity.ok(entity1);

        } catch (Exception e) {
            System.out.println("bitki controllerda data yükleme işlmei başarısız :" + e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteBitki(@PathVariable Long id) {

        service.deleteBitki(id);

        return ResponseEntity.noContent().build();
    }

}
