package com.example.facapp.repository;

import com.example.facapp.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 姜涛
 * @create 2018-04-14 12:18
 * @desc 账户表client的dao层
 **/
@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    ClientModel findFirstByXh(Long xh);
}
