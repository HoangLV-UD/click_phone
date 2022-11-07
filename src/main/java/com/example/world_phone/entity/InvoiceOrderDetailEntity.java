package com.example.world_phone.entity;

import javax.persistence.*;

@Entity
@Table(name = "invoiceorderdetail", schema = "world_phone", catalog = "")
public class InvoiceOrderDetailEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "MONEY_INVOICE")
    private Long moneyInvoice;

    @Basic
    @Column(name = "QUANTITY_INVOICE")
    private Long quantityInvoice;

    @Basic
    @Column(name = "note")
    private String note;


    @Basic
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ROM_ID", referencedColumnName = "ID")
    private RomEntity romEntity;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ORDER_ID", referencedColumnName = "ID")
    private InvoiceOrderEntity invoiceOrderEntity;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID", referencedColumnName = "ID")
    private ColorEntity colorEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoneyInvoice() {
        return moneyInvoice;
    }

    public void setMoneyInvoice(Long moneyInvoice) {
        this.moneyInvoice = moneyInvoice;
    }

    public Long getQuantityInvoice() {
        return quantityInvoice;
    }

    public void setQuantityInvoice(Long quantityInvoice) {
        this.quantityInvoice = quantityInvoice;
    }

    public RomEntity getRomEntity() {
        return romEntity;
    }

    public void setRomEntity(RomEntity romEntity) {
        this.romEntity = romEntity;
    }

    public InvoiceOrderEntity getInvoiceOrderEntity() {
        return invoiceOrderEntity;
    }

    public void setInvoiceOrderEntity(InvoiceOrderEntity invoiceOrderEntity) {
        this.invoiceOrderEntity = invoiceOrderEntity;
    }

    public ColorEntity getColorEntity() {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
