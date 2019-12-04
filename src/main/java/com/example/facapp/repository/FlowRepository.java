package com.example.facapp.repository;

import com.example.facapp.model.FlowModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 姜涛
 * @create 2018-04-14 13:18
 * @desc 生产流程表对应dao层
 **/
public interface FlowRepository extends JpaRepository<FlowModel, Long> {
    FlowModel findFirstByCjbhAndBzjb(Long cjbh, String bzjb);
}
