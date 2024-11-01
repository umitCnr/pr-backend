package com.pr.project_backend.service;

import com.pr.project_backend.Dto.AkvaryumDto;
import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.repository.FishRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {

    private final FishRepo repo;
    private final MinioService service;

    public List<AkvaryumDto> getFishAll(){
        List<FishEntity> fishEntities = repo.findAll();
        List<AkvaryumDto> fishDtos = new ArrayList<>();

        for (FishEntity fishEntity : fishEntities) {
            AkvaryumDto fishDto = new AkvaryumDto();
            fishDto.setId(fishEntity.getId());
            fishDto.setName(fishEntity.getName());
            fishDto.setKind(fishEntity.getKind());
            fishDto.setDescription(fishEntity.getDescription());
            try {
                String base64Image = service.getAllFile(fishEntity.getUrl());
                fishDto.setImgUrl(base64Image);
            } catch (Exception e) {
                System.out.println("Resim alınırken hata oluştu: " + e.getMessage());
                fishDto.setImgUrl(null);
            }

            fishDtos.add(fishDto);
        }

        return fishDtos;
    }

    public List<FishEntity> getKindToFish(FishEntity.Kind kind) {
        return repo.findByKind(kind);
    }

    public FishEntity saveFish(MultipartFile file, FishEntity fishEntity) throws IOException {

        if (fishEntity.getKind() == null) {
            throw new IllegalArgumentException("tür bulunamadı");
        }

        String getImgName = file.getOriginalFilename();
        try {

            String uploadImg = service.uploadImageMinio(file, getImgName);
            fishEntity.setUrl(uploadImg);

        } catch (Exception e) {
            throw new IOException("Resim yüklenirken hata oluştu: " + e.getMessage(), e);
        }

        return repo.save(fishEntity);
    }


    public FishEntity updateFish(Long id, FishEntity fishEntity) {

        FishEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("güncellenecek balık bulunamadı!" + id));

        entity.setName(fishEntity.getName());
        entity.setKind(fishEntity.getKind());
        entity.setDescription(fishEntity.getDescription());
        entity.setUrl(fishEntity.getUrl());

        return repo.save(entity);
    }

    public void deleteFish(Long id) {

        FishEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("balık bulunamadı!" + id));
        repo.deleteById(entity.getId());
    }

}
