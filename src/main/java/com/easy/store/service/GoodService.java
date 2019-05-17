package com.easy.store.service;

import com.easy.store.repository.jpa.entity.Good;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodService {

    List<Good> findAll();

    Good findById(String id);
}
