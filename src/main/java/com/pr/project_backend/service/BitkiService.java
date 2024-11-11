package com.pr.project_backend.service;

import com.pr.project_backend.Dto.BitkiDto;
import com.pr.project_backend.data.BitkiEntity;
import com.pr.project_backend.repository.BitkiRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BitkiService {

    private final BitkiRepo repo;
    private final MinioService service;

    public List<BitkiDto> getAllBitkiList() {

        List<BitkiEntity> entities = repo.findAll();

        List<BitkiDto> dtos = new ArrayList<>();

        for (BitkiEntity bitkiEntity : entities) {

            BitkiDto dto = new BitkiDto();

            dto.setId(bitkiEntity.getId());
            dto.setDescription(bitkiEntity.getDescription());
            dto.setName(bitkiEntity.getName());
            dto.setMax_temp(bitkiEntity.getMax_temp());
            dto.setMin_temp(bitkiEntity.getMin_temp());
            dto.setTime(bitkiEntity.getTime());
            dto.setKind(bitkiEntity.getKind());

            try {

                String base64Image = service.getAllFile(bitkiEntity.getUrl());
                dto.setUrl(base64Image);

            } catch (Exception e) {
                System.out.println("bitkiController dataları getirildi :" + e);
            }

            dtos.add(dto);
        }

        return dtos;

    }

    public List<BitkiDto> getBitkiList(BitkiEntity.Kind kind) {

        List<BitkiEntity> entities = repo.findByKind(kind);

        List<BitkiDto> dtos = new ArrayList<>();

        for (BitkiEntity bitkiEntity : entities) {

            BitkiDto dto = new BitkiDto();

            dto.setId(bitkiEntity.getId());
            dto.setDescription(bitkiEntity.getDescription());
            dto.setName(bitkiEntity.getName());
            dto.setMax_temp(bitkiEntity.getMax_temp());
            dto.setMin_temp(bitkiEntity.getMin_temp());
            dto.setTime(bitkiEntity.getTime());
            dto.setKind(bitkiEntity.getKind());

            try {

                String img = service.getAllFile(bitkiEntity.getUrl());
                dto.setUrl(img);

            } catch (Exception e) {
                System.out.println("bitkiController dataları getirildi :" + e);
            }

            dtos.add(dto);
        }

        return dtos;
    }

    public BitkiEntity saveBitki(BitkiEntity bitkiEntity, MultipartFile file) {

        if (bitkiEntity.getKind() == null) {
            throw new IllegalArgumentException("bitki türü bulunamadı!");
        }

        String fileMinio = file.getOriginalFilename();
        try {

            String imgUrl = service.uploadImageMinio(file, fileMinio);
            bitkiEntity.setUrl(imgUrl);
        } catch (Exception e) {
            System.out.println("bitki datası yüklenemedi" + e);
        }


        return repo.save(bitkiEntity);
    }


    public void deleteBitki(Long id) {

        BitkiEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("bitki bulunamadı!" + id));

        if (entity.getUrl() != null) {
            String fileName = entity.getUrl();
            service.deleteFile(fileName);
        }

        repo.deleteById(entity.getId());
    }

}
