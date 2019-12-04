package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.OrderModel;
import com.example.facapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class BeginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderRepository orderRepository;

    /*
    * 判断订单是否存在
    * */
    public boolean orderExist(String cjbh, String orderId) {
        Map param = new HashMap();
        param.put("ddh", orderId);
        param.put("cjbh", cjbh);
        Long count = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT_BY_ID, param), Long.class);
        return count==1;
    }

    /*
     * 判断bzjb是否存在
     * */
    public boolean flowExist(String cjbh, String bzjb) {
        Map param = new HashMap();
        param.put("bzjb", bzjb);
        param.put("cjbh", cjbh);
        Long count = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_FLOW_COUNT_BY_ID, param), Long.class);
        return count>0;
    }

    public Long getOrderXh(String cjbh, String orderId) {
        Map param = new HashMap();
        param.put("ddh", orderId);
        param.put("cjbh", cjbh);
        return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_XH_BY_ID, param), Long.class);
    }

    public boolean update(Long xh, String bzjb) {
        OrderModel one = orderRepository.findFirstByXh(xh);
        one.setBzjb(bzjb);
        OrderModel save = orderRepository.save(one);
        return Objects.nonNull(save);
    }


}
