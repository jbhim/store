package com.easy.store.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.easy.store.repository.jpa.entity.Account;
import com.easy.store.service.UserService;
import com.easy.store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
@Slf4j
public class OrderController extends BaseController {
    private final UserService userService;

    @Autowired
    public OrderController(UserService userService) {
        this.userService = userService;
    }

}
