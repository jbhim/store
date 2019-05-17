package com.easy.store.service.impl;

import com.easy.store.model.ShopCarModel;
import com.easy.store.repository.jpa.entity.ShopCar;
import com.easy.store.repository.jpa.repository.GoodRepository;
import com.easy.store.repository.jpa.repository.ShopCarRepository;
import com.easy.store.service.ShopCarService;
import com.easy.store.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbhim
 * @date 2019/5/16/016.
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Resource
    private ShopCarRepository shopCarRepository;
    @Resource
    private GoodRepository goodRepository;

    @Override
    public void save(ShopCar shopCar, String id) {
        shopCar.setId(UUIDGenerator.sequentialUUIDString());
        shopCar.setUserId(id);
        shopCarRepository.save(shopCar);
    }

    @Override
    public List<ShopCarModel> findAll(String id) {
        List<ShopCar> shopCarList = shopCarRepository.findByUserId(id);
        List<ShopCarModel> shopCarModels = new ArrayList<>();
        shopCarList.forEach(shopCar -> {
            ShopCarModel shopCarModel = new ShopCarModel();
            String goodId = shopCar.getGoodId();
            goodRepository.findById(goodId).ifPresent(good -> {
                        shopCarModel.setId(shopCar.getId());
                        shopCarModel.setCount(shopCar.getGoodCount());
                        shopCarModel.setName(good.getName());
                        shopCarModel.setPrice(good.getNewPrice());
                        shopCarModel.setSrc(good.getSrc());
                        shopCarModels.add(shopCarModel);
                    }
            );
        });
        return shopCarModels;
    }
}
