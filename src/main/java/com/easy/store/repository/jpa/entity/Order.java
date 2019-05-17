package com.easy.store.repository.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author jbhim
 * @date 2019/5/15/015.
 */
@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @Column(length = 64)
    private String id;

    private String goodId;

    private int count;

    private BigDecimal allPrice;

    private Boolean isfh;

    private String storeName;

    private String name;

    @Transient
    private String img;

    private String userId;
}
