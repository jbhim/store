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
@RequestMapping("api/user")
@Slf4j
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResultVO login(@RequestBody Account account) {
        Account loginInfo = userService.loadUsernameAndPassword(account.getUsername(), account.getPassword());
        loginInfo.setPassword(null);
        getSession().setAttribute(LOGININFO, JSONUtils.toJSONString(loginInfo));
        return ResultVO.success(loginInfo);
    }

    @PostMapping("sign")
    public ResultVO test(@RequestBody Account account) {
        return ResultVO.success(userService.save(account));
    }

}
