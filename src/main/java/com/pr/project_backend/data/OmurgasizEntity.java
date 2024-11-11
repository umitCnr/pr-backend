package com.pr.project_backend.data;

import com.pr.project_backend.mainEntity.MainEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "omurgasiz")
@Getter
@Setter
public class OmurgasizEntity extends MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "kind",nullable = false)
    private OmurgasizEntity.Kind kind;

    @Column(name = "img_url")
    private String url;

    public enum Kind {
        TATLI, TUZLU
    }
}
