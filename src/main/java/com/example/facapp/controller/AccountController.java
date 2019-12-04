package com.example.facapp.controller;

import com.example.facapp.service.AccountService;
import com.example.facapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姜涛
 * @create 2018-04-17 13:42
 * @desc 账户控制层
 **/
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/validate")
    public String list(String username, String password) {
        String result = "error";
        try {
            if (accountService.validate(username,password)){
                result = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/modify")
    public void modify(Long username, String newPwd) {
        try {
            if (accountService.modify(username, newPwd)){
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/validate1")
    public Map validate1(String username, String password) {
        Map result = new HashMap();
        result.put("valid", false);
        try {
            if (accountService.validate(username,password)){
                result.put("valid", true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/get_cjmc")
    public String getCjmc(Long cjbh) {
        String result = "搜索功能";
        try {
            result = accountService.getCjmc(cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
