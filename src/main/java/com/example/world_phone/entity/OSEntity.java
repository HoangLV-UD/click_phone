package com.example.world_phone.entity;

import javax.persistence.*;

@Entity
@Table(name = "os", schema = "world_phone", catalog = "")
public class OSEntity extends BaseEntity{ // Hệ điều hành

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOAI_OS")
    private Integer loai;

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

    public Integer getLoai() {
        return loai;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }
}
