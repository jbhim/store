package com.easy.store.repository.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author jbhim
 * @date 2019/5/15/015.
 */
@Data
@Entity
@Table(name = "order")
public class ShopCar {
    @Id
    @Column(length = 64)
    private String id;
    private String goodId;
    //商品数量
    private String goodCount;
    private String userId;
    private BigDecimal price;
}
