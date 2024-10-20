package com.pr.project_backend.controller;

import com.pr.project_backend.data.BitkiEntity;
import com.pr.project_backend.service.BitkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bitki")
public class BitkiController {

    private final BitkiService service;

    @GetMapping("/getKind/{kind}")
    private ResponseEntity<List<BitkiEntity>> getKindBitki(@PathVariable BitkiEntity.Kind kind) {

        List<BitkiEntity> entity = service.getBitkiList(kind);

        return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
    }

    @GetMapping("/getAll")
    private ResponseEntity<List<BitkiEntity>> getAllBitki(){

        List<BitkiEntity> entities = service.getAllBitkiList();

        return entities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(entities);

    }

    @PostMapping("/save")
    private ResponseEntity<BitkiEntity> saveBitki(@RequestBody BitkiEntity bitkiEntity){

        BitkiEntity entity = service.saveBitki(bitkiEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<BitkiEntity> updateBitki(@PathVariable Long id ,@RequestBody BitkiEntity bitkiEntity){

        BitkiEntity entity = service.updateBitki(id,bitkiEntity);

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteBitki(@PathVariable Long id){

        Optional<BitkiEntity> entity = service.findByIdBitki(id);

        if (entity.isPresent()){
            service.deleteBitki(id);
            return ResponseEntity.ok("Bitki başarıyla silindi!");
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bitki bulunamadı, silinemedi!");
    }

}
