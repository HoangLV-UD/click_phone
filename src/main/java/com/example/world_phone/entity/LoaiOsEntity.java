package com.example.world_phone.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "loai_os", schema = "world_phone", catalog = "")
public class LoaiOsEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "LoaiOsEntity")
    private List<OSEntity> osEntities;

    public LoaiOsEntity() {
    }

    public LoaiOsEntity(Long valueOf) {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OSEntity> getOsEntities() {
        return osEntities;
    }

}
