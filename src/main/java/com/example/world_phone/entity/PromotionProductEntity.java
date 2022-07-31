package com.example.world_phone.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "promotion_product", schema = "world_phone", catalog = "")
public class PromotionProductEntity extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_id", nullable = false)
    private String promotionId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
