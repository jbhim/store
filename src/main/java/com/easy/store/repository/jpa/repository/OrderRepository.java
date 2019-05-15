package com.easy.store.repository.jpa.repository;


import com.easy.store.repository.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
