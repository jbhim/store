package com.easy.store.controller;


import com.easy.store.repository.jpa.entity.Account;
import com.easy.store.repository.jpa.repository.AccountRepository;
import com.easy.store.service.UserService;
import com.easy.store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController extends BaseController {
    private final UserService userService;
    private final AccountRepository accountRepository;

    @Autowired
    public UserController(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("login")
    public ResultVO login(@RequestBody Account account) {
        Account loginInfo = userService.loadUsernameAndPassword(account.getUsername(), account.getPhone(), account.getPassword());
        loginInfo.setPassword(null);
        getSession().setAttribute(LOGININFO, loginInfo);
        return ResultVO.success(loginInfo);
    }

    @PostMapping("sign")
    public ResultVO test(@RequestBody Account account) {
        return ResultVO.success(userService.save(account));
    }

    @GetMapping("getCurrentUser")
    public ResultVO getCurrentUser() {
        return ResultVO.success(getCurrentUserInfo());
    }

    @GetMapping("logout")
    public ResultVO logout() {
        getSession().removeAttribute(LOGININFO);
        return ResultVO.success();
    }

}
