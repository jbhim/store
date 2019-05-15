package com.easy.store.repository.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jbhim
 * @date 2019/5/15/015.
 */
@Data
@Entity
@Table(name = "shop")
public class Good {
    @Id
    @Column(length = 64)
    private String id;
    private String name;
    private String oldPrice;
    private String newPrice;
    private String xsPrice;
    private String count;
    private String restCount;
    private String src;
    private String imgs;
}
