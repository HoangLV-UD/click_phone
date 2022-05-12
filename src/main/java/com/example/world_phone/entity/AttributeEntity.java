package com.example.world_phone.entity;

import javax.persistence.*;

@Entity
@Table(name = "attribute", schema = "world_phone", catalog = "")
public class AttributeEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "OS")
    private String operatingSystem; // he dieu hanh

    @Basic
    @Column(name = "SCREEN")
    private String screen; // man hinh

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
    @Column(name = "RAM")
    private String ram;

    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
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

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
