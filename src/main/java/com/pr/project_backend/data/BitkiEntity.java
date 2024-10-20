package com.pr.project_backend.data;

import com.pr.project_backend.mainEntity.MainEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bitki")
public class BitkiEntity extends MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind",nullable = false)
    private Kind kind;

    @Column(name = "min_temperature",nullable = false)
    private long min_temp;

    @Column(name = "max_temperature",nullable = false)
    private long max_temp;

    @Column(name = "description",nullable = false)
    private String description;

    public enum Kind {
        TUZLU, TATLI
    }


}
