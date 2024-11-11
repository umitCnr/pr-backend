package com.pr.project_backend.Dto;

import com.pr.project_backend.data.FishEntity;
import com.pr.project_backend.data.OmurgasizEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class OmurgasizDto {

    public long id;
    public String name;
    public OmurgasizEntity.Kind kind;
    public String description;
    public String imgUrl;
    public LocalDateTime time;


}
