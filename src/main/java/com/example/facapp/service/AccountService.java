package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.AccountModel;
import com.example.facapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 姜涛
 * @create 2018-04-17 13:44
 * @desc 客户业务逻辑层
 **/
@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountRepository accountRepository;

    public boolean validate(String username, String password) {
        Map param = new HashMap();
        param.put("username", username);
        param.put("password", password);
        param.put("cjqx", "是");
        return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.VALIDATE_ACCOUNT, param), Integer.class) == 1;
    }

    public boolean modify(Long username, String newPwd) {
        AccountModel one = accountRepository.getOne(username);
        if (Objects.isNull(one)){
            return false;
        }
        one.setPassword(newPwd);
        AccountModel save = accountRepository.saveAndFlush(one);
        return Objects.nonNull(save);
    }

    public String getCjmc(Long cjbh) {
        AccountModel one = accountRepository.getOne(cjbh);
        return one.getCjmc();
    }
}
