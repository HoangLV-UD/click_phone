package com.example.world_phone.entity;

import javax.persistence.*;

@Entity
@Table(name = "property_product", schema = "world_phone", catalog = "")
public class ProductPropertyEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "PRICE")
    private long price;


    @Basic
    @Column(name = "STATUS")
    private String status;


    @Basic
    @Column(name = "QUANTITY")
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "ROM_ID", referencedColumnName = "ID")
    private RomEntity romEntity;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID", referencedColumnName = "ID")
    private ColorEntity colorEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public RomEntity getRomEntity() {
        return romEntity;
    }

    public void setRomEntity(RomEntity romEntity) {
        this.romEntity = romEntity;
    }

    public ColorEntity getColorEntity()  {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }
}
