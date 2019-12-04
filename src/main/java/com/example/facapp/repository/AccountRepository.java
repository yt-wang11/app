package com.example.facapp.repository;

import com.example.facapp.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 姜涛
 * @create 2018-04-14 13:04
 * @desc 账户分配表account的dao层
 **/
@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
