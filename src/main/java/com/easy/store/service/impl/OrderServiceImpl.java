package com.easy.store.service.impl;

import com.easy.store.repository.jpa.entity.Order;
import com.easy.store.repository.jpa.repository.GoodRepository;
import com.easy.store.repository.jpa.repository.OrderRepository;
import com.easy.store.service.OrderService;
import com.easy.store.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jbhim
 * @date 2019/5/16/016.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private GoodRepository goodRepository;

    @Override
    public List<Order> findAll(String id) {
        return orderRepository.findAllByUserId(id).stream().peek(order -> {
            goodRepository.findById(order.getGoodId()).ifPresent(good -> {
                order.setImg(good.getSrc());
            });
        }).collect(Collectors.toList());
    }

    @Override
    public void save(Order order) {
        order.setId(UUIDGenerator.sequentialUUIDString());
        orderRepository.save(order);
    }
}
