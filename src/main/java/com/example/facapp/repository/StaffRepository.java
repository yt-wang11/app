package com.example.facapp.repository;

import com.example.facapp.model.ClientModel;
import com.example.facapp.model.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 姜涛
 * @create 2018-04-14 12:18
 * @desc 账户表client的dao层
 **/
@Repository
public interface StaffRepository extends JpaRepository<StaffModel, Long> {
    List<StaffModel> findByNameLike(String name);
    List<StaffModel> findAllById(Integer id);
}
