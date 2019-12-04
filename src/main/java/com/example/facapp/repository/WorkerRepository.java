package com.example.facapp.repository;

import com.example.facapp.model.WorkerModel;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 姜涛
 * @create 2018-04-14 13:57
 * @desc 厂家工人表dao层
 **/
public interface WorkerRepository extends JpaRepository<WorkerModel, Long> {
    List<WorkerModel> findAllByCjbhAndAndGrxm(Long cjbh, String grxm);
    List<WorkerModel> findAllByCjbh(Long cjbh);
    WorkerModel findFirstByGrxmAndCjbh(String grxm, Long cjbh);

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Integer deleteAllByCjbhAndAndGrxm(Long cjbh, String grxm);
}
