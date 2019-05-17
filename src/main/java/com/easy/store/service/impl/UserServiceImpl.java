package com.easy.store.service.impl;

import com.easy.store.exception.CustomException;
import com.easy.store.repository.jpa.entity.Account;
import com.easy.store.repository.jpa.repository.AccountRepository;
import com.easy.store.service.UserService;
import com.easy.store.utils.UUIDGenerator;
import com.easy.store.utils.enums.ResultEnum;
import com.easy.store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account loadUsernameAndPassword(String username, String phone, String password) {
        Optional<Account> byUsername = accountRepository.findByUsernameOrPhone(username, phone);
        Account account = byUsername.orElseThrow(() -> new CustomException(ResultEnum.USER_NOT_FOUND));
        if (!account.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new CustomException(ResultEnum.PASSWORD_ERROR);
        }
        return account;
    }

    @Override
    public ResultVO save(Account account) {
        if (StringUtils.isEmpty(account.getId())) {
            account.setId(UUIDGenerator.sequentialUUIDString());
            account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        } else {
            Account user = accountRepository.findById(account.getId()).orElse(null);
            assert user != null;
            account.setPassword(user.getPassword());
        }
        Account save = accountRepository.save(account);
        if (save == null) {
            throw new CustomException(ResultEnum.FAILURE);
        }
        return ResultVO.success();
    }
}
