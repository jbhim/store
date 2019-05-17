package com.easy.store.service;

import com.easy.store.repository.jpa.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll(String id);

    void save(Order order);
}
