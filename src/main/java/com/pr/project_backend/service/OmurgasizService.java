package com.pr.project_backend.service;

import com.pr.project_backend.Dto.AkvaryumDto;
import com.pr.project_backend.Dto.OmurgasizDto;
import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.data.OmurgasizEntity;
import com.pr.project_backend.repository.OmurgasizRepo;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OmurgasizService {

    private final OmurgasizRepo repo;
    private final MinioService service;

    public List<OmurgasizDto> getOmurgasizAll() {

        List<OmurgasizEntity> fishEntities = repo.findAll();
        List<OmurgasizDto> fishDtos = new ArrayList<>();

        for (OmurgasizEntity omurgasizEntity : fishEntities) {
            OmurgasizDto omurgasizDto = new OmurgasizDto();
            omurgasizDto.setId(omurgasizEntity.getId());
            omurgasizDto.setName(omurgasizEntity.getName());
            omurgasizDto.setKind(omurgasizEntity.getKind());
            omurgasizDto.setDescription(omurgasizEntity.getDescription());
            omurgasizDto.setTime(omurgasizEntity.getTime());
            try {
                String base64Image = service.getAllFile(omurgasizEntity.getUrl());
                omurgasizDto.setImgUrl(base64Image);
            } catch (Exception e) {
                System.out.println("Resim alınırken hata oluştu: " + e.getMessage());
                omurgasizDto.setImgUrl(null);
            }

            fishDtos.add(omurgasizDto);
        }

        return fishDtos;
    }

    public List<OmurgasizDto> getKindToOmurgasiz(OmurgasizEntity.Kind kind) {

        List<OmurgasizEntity> entities = repo.findByKind(kind);
        List<OmurgasizDto> omurgasizDtos = new ArrayList<>();

        for (OmurgasizEntity entity : entities) {
            OmurgasizDto omurgasizDto = new OmurgasizDto();


            omurgasizDto.setId(entity.getId());
            omurgasizDto.setName(entity.getName());
            omurgasizDto.setKind(entity.getKind());
            omurgasizDto.setDescription(entity.getDescription());
            omurgasizDto.setTime(entity.getTime());

            try {
                String base64Image = service.getAllFile(entity.getUrl());
                omurgasizDto.setImgUrl(base64Image);
            } catch (Exception e) {
                System.out.println("Resim alınırken hata oluştu: " + e.getMessage());
                omurgasizDto.setImgUrl(null);
            }

            omurgasizDtos.add(omurgasizDto);
        }

        return omurgasizDtos;
    }

    public OmurgasizEntity saveOmurgasiz(OmurgasizEntity entity, MultipartFile file) {

        String getImg = file.getOriginalFilename();

        try {

            String uploadOmurgasiz = service.uploadImageMinio(file, getImg);
            entity.setUrl(uploadOmurgasiz);

        } catch (Exception e) {
            System.out.println("omurgasız data yükleyemedi");
        }
        return repo.save(entity);
    }

    public Optional<OmurgasizEntity> findByOptianal(Long id) {

        return repo.findById(id);
    }


    public void deleteFish(Long id) {

        OmurgasizEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("balık bulunamadı!" + id));

        if (entity.getUrl() != null) {
            String fileName = entity.getUrl();
            service.deleteFile(fileName);
        }

        repo.deleteById(entity.getId());
    }

}
