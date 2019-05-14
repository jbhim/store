package com.easy.store.repository.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(length = 64)
    private String id;

    @Column
    private String address;

    @Column
    private String userId;
}
