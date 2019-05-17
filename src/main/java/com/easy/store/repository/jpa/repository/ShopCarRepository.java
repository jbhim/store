package com.easy.store.repository.jpa.repository;


import com.easy.store.repository.jpa.entity.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCarRepository extends JpaRepository<ShopCar, String> {
    List<ShopCar> findByUserId(String userId);

    int countByUserIdAndGoodId(String userId, String goods);
}
