package com.easy.store.service;

import com.easy.store.model.ShopCarModel;
import com.easy.store.repository.jpa.entity.ShopCar;

import java.util.List;

public interface ShopCarService {

    void save(ShopCar shopCar, String id);

    List<ShopCarModel> findAll(String id);

}
