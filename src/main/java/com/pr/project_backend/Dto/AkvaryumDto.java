package com.pr.project_backend.Dto;

import com.pr.project_backend.data.FishEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class AkvaryumDto {

    public long id;
    public String name;
    public FishEntity.Kind kind;
    public String description;
    public String imgUrl;
    public LocalDateTime time;

}
