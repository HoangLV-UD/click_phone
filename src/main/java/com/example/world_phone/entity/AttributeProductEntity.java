package com.example.world_phone.entity;

import javax.persistence.*;

@Entity
@Table(name = "attribute_product", schema = "world_phone", catalog = "")
public class AttributeProductEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "OS_ID")
    private Integer osId; // he dieu hanh

    @Basic
    @Column(name = "SCREEN_ID")
    private Integer screenId; // man hinh

    @Basic
    @Column(name = "CHIP")
    private String chip;

    @Basic
    @Column(name = "CAM_TRUOC")
    private String camTruoc;

    @Basic
    @Column(name = "CAM_SAU")
    private String camSau;

    @Basic
    @Column(name = "SIM")
    private String sim;

    @Basic
    @Column(name = "PIN")
    private String pin;

    @Basic
    @Column(name = "RAM_ID")
    private Integer ramID;

    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOsId() {
        return osId;
    }

    public void setOsId(Integer osId) {
        this.osId = osId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getCamTruoc() {
        return camTruoc;
    }

    public void setCamTruoc(String camTruoc) {
        this.camTruoc = camTruoc;
    }

    public String getCamSau() {
        return camSau;
    }

    public void setCamSau(String camSau) {
        this.camSau = camSau;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getRamID() {
        return ramID;
    }

    public void setRamID(Integer ramID) {
        this.ramID = ramID;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
