package com.easy.store.repository.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "goods")
public class Good {
    @Id
    @Column(length = 64)
    private String id;
    private String name;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private BigDecimal xsPrice;
    private int count;
    private int restCount;
    private String src;
    private int sum;
    private String imgs;
    private Date time;
}
