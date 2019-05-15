package com.easy.store.controller;


import com.easy.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/goods")
@Slf4j
public class GoodController extends BaseController {
    private final UserService userService;

    @Autowired
    public GoodController(UserService userService) {
        this.userService = userService;
    }

}
