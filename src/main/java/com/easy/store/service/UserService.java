package com.easy.store.service;

import com.easy.store.repository.jpa.entity.Account;
import com.easy.store.vo.ResultVO;

public interface UserService {
    Account loadUsernameAndPassword(String username, String phone, String password);

    ResultVO save(Account account);
}
