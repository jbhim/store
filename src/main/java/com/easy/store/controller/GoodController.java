package com.easy.store.controller;


import com.easy.store.model.ShopCarModel;
import com.easy.store.repository.jpa.entity.Good;
import com.easy.store.repository.jpa.entity.Order;
import com.easy.store.repository.jpa.entity.ShopCar;
import com.easy.store.service.GoodService;
import com.easy.store.service.OrderService;
import com.easy.store.service.ShopCarService;
import com.easy.store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
public class GoodController extends BaseController {
    private final GoodService goodService;
    private final ShopCarService shopCarService;
    private final OrderService orderService;

    @Autowired
    public GoodController(GoodService goodService, ShopCarService shopCarService, OrderService orderService) {
        this.goodService = goodService;
        this.shopCarService = shopCarService;
        this.orderService = orderService;
    }

    @GetMapping("goods")
    public ResultVO findAll() {
        List<Good> goods = goodService.findAll();
        return ResultVO.success(goods);
    }

    @GetMapping("goods/{id}")
    public ResultVO findAll(@PathVariable("id") String id) {
        Good good = goodService.findById(id);
        return ResultVO.success(good);
    }

    @GetMapping("shopCar")
    public ResultVO shopCar() {
        List<ShopCarModel> goods = shopCarService.findAll(getCurrentUserInfo().getId());
        return ResultVO.success(goods);
    }

    @PostMapping("addShopCar")
    public ResultVO addShopCar(@RequestBody ShopCar shopCar) {
        shopCarService.save(shopCar, getCurrentUserInfo().getId());
        return ResultVO.success();
    }

    @PostMapping("addOrder")
    public ResultVO addOrder(@RequestBody Order order) {
        String id = getCurrentUserInfo().getId();
        order.setUserId(id);
        orderService.save(order);
        return ResultVO.success();
    }


    @GetMapping("order")
    public ResultVO order() {
        List<Order> orders = orderService.findAll(getCurrentUserInfo().getId());
        return ResultVO.success(orders);
    }
}
