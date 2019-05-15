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
@Table(name = "order")
public class Order {
    @Id
    @Column(length = 64)
    private String id;
}
