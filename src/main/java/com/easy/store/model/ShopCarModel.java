package com.easy.store.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jbhim
 * @date 2019/5/16/016.
 */
@Data
public class ShopCarModel {
    private String id;

    private String name;

    private BigDecimal price;

    private int count;

    private String src;
}
