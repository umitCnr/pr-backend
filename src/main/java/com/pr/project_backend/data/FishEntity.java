package com.pr.project_backend.data;

import com.pr.project_backend.mainEntity.MainEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fish")
public class FishEntity extends MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind", nullable = false)
    private Kind kind;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "img_url")
    private String url;

    public enum Kind {
        TUZLU, TATLI
    }
}
