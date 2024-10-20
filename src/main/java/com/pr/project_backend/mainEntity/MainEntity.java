package com.pr.project_backend.mainEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class MainEntity {

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "update_time")
    private LocalDateTime update_time;

    @PrePersist
    public void prePersist(){
        this.time=LocalDateTime.now();
    }

    @PreUpdate
    public  void preUpdate(){
        this.update_time = LocalDateTime.now();
    }

}
