package com.example.facapp.repository;

import com.example.facapp.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 姜涛
 * @create 2018-04-14 13:41
 * @desc 订单表dao层
 **/
@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderModel, Long> {

    List<OrderModel> findAllByDdhAndCjbh(String ddh, Long cjbh);

    OrderModel findFirstByXh(Long xh);
}
