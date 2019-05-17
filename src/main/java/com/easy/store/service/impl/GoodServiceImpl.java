package com.easy.store.service.impl;

import com.easy.store.repository.jpa.entity.Good;
import com.easy.store.repository.jpa.repository.GoodRepository;
import com.easy.store.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jbhim
 * @date 2019/5/16/016.
 */
@Service
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;

    @Autowired
    public GoodServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Override
    public Good findById(String id) {
        return goodRepository.findById(id).orElse(null);
    }
}
