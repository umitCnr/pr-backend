package com.pr.project_backend.Dto;

import com.pr.project_backend.data.BitkiEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BitkiDto {

    private long id;
    private String name;
    private BitkiEntity.Kind kind;
    private long min_temp;
    private long max_temp;
    private String description;
    private String url;
    public LocalDateTime time;

}
