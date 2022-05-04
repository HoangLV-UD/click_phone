package com.example.world_phone.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "world_phone", catalog = "")
public class CategoryEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "PARENT_ID")
    private String parentId;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;
    @Basic
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Basic
    @Column(name = "MODIFIER_DATE")
    private Date modifierDate;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "MODIFIER_BY")
    private String modifierBy;
    @Basic
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }



    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public void setModifierDate(Timestamp modifierDate) {
        this.modifierDate = modifierDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifierBy() {
        return modifierBy;
    }

    public void setModifierBy(String modifierBy) {
        this.modifierBy = modifierBy;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}