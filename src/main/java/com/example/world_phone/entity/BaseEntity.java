package com.example.world_phone.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_date")
    private Timestamp createDate;

    @LastModifiedDate
    @Column(name = "MODIFIER_DATE")
    private Timestamp modifierDate;

    @CreatedBy
    @Column(name = "CREATE_BY")
    private String createBy = "ADMIN";

    @LastModifiedBy
    @Column(name = "MODIFIER_BY")
    private String modifierBy = "ADMIN";

    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag = false;

    @PrePersist
    public void prePersist() {
        createDate = new Timestamp(System.currentTimeMillis());
        modifierDate = createDate;
    }

    @PreUpdate
    public void preUpdate() {
        modifierDate = new Timestamp(System.currentTimeMillis());
    }

}