package com.easy.store.repository.jpa.repository;


import com.easy.store.repository.jpa.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, String> {
}
