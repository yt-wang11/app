package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.OrderListModel;
import com.example.facapp.model.OrderModel;
import com.example.facapp.repository.ClientRepository;
import com.example.facapp.repository.FlowRepository;
import com.example.facapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author 姜涛
 * @create 2018-04-14 14:15
 * @desc 订单service
 **/
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*分页查询订单列表*/
    @SuppressWarnings("all")
    public List<OrderListModel> findAllByPage(int limit, int offset, String ddh, String cjbh){
        List<OrderModel> orderModels;
        if (!StringUtils.isEmpty(ddh)) {
            orderModels = orderRepository.findAllByDdhAndCjbh(ddh, Long.parseLong(cjbh));
        }else {
            Map param = new HashMap();
            param.put("cjbh", cjbh);
            param.put("limit", limit);
            param.put("offset", offset);
            orderModels = jdbcTemplate.query(ParameterBind.bind(SQL.FIND_ORDER_BY_CJ, param), new BeanPropertyRowMapper<>(OrderModel.class));
        }
        List<OrderListModel> result = new ArrayList<>();
        orderModels.forEach(t -> {
            OrderListModel listModel = new OrderListModel();
            listModel.setXh(t.getXh());
            listModel.setCustomerName(clientRepository.findFirstByXh(t.getKhbh()).getKhmc());
            listModel.setFlowName(flowRepository.findFirstByCjbhAndBzjb(t.getCjbh(), t.getBzjb()).getBzmc());
            listModel.setOrderId(t.getDdh());
            listModel.setOrderTime(t.getDdsj().toString().substring(0, 10));
            result.add(listModel);
        });

        return result;
    }

    /*查询某客户所有订单*/
    @SuppressWarnings("all")
    public List<OrderListModel> findAllByClient(Long cjbh, Long khbh){
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        param.put("khbh", khbh);
        List<OrderModel> orderModels = jdbcTemplate.query(ParameterBind.bind(SQL.FIND_ORDER_BY_CJ_AND_KHBH, param), new BeanPropertyRowMapper<>(OrderModel.class));
        List<OrderListModel> result = new ArrayList<>();
        orderModels.forEach(t -> {
            OrderListModel listModel = new OrderListModel();
            listModel.setXh(t.getXh());
            listModel.setCustomerName(clientRepository.findFirstByXh(t.getKhbh()).getKhmc());
            listModel.setFlowName(flowRepository.findFirstByCjbhAndBzjb(t.getCjbh(), t.getBzjb()).getBzmc());
            listModel.setOrderId(t.getDdh());
            listModel.setOrderTime(t.getDdsj().toString().substring(0, 10));
            result.add(listModel);
        });

        return result;
    }

    public Long count(String cjbh, String search){
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        if (!StringUtils.isEmpty(search)){
            param.put("ddh", search);
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT_BY_ID, param), Long.class);
        }else {
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT, param), Long.class);
        }
    }

    public boolean delete(String ids){
        Map param = new HashMap();
        param.put("ids", ids);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_ORDER_BY_XHS, param));
        return update!=0;
    }

    public void deleteByKhbh(String khbh){
        Map param = new HashMap();
        param.put("khbh", khbh);
        jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_ORDER_BY_KHBH, param));
    }

    @SuppressWarnings("all")
    public boolean insert(Long cjbh, Long khbh, String ddh, String bzjb, Timestamp ddsj){
        int update = jdbcTemplate.update(SQL.INSERT_ORDER, t ->{
            t.setLong(1, cjbh);
            t.setLong(2, khbh);
            t.setString(3, ddh);
            t.setString(4, bzjb);
            t.setTimestamp(5, ddsj);
        });
        return update!=0;
    }

    @SuppressWarnings("all")
    public boolean update(Long xh, Long cjbh, Long khbh, String ddh, String bzjb, Timestamp ddsj){
        OrderModel one = orderRepository.findFirstByXh(xh);
        one.setCjbh(cjbh);
        one.setKhbh(khbh);
        one.setDdh(ddh);
        one.setBzjb(bzjb);
        one.setDdsj(ddsj);
        OrderModel save = orderRepository.save(one);
        /*int update = jdbcTemplate.update(SQL.UPDATE_ORDER, t ->{
            t.setLong(1, cjbh);
            t.setLong(2, khbh);
            t.setString(3, ddh);
            t.setString(4, bzjb);
            t.setTimestamp(5, ddsj);
            t.setLong(6, xh);
        });*/
        return Objects.nonNull(save);
    }

    public Map<String, Object> show(Long xh){
        Map param = new HashMap();
        param.put("xh", xh);
        return jdbcTemplate.queryForMap(ParameterBind.bind(SQL.SHOW_ORDER_BY_XH, param));
    }

    public boolean exist(Long ddh, Long cjbh){
        Map param = new HashMap();
        param.put("ddh", ddh);
        param.put("cjbh", cjbh);
        Long count = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.EXIST_BY_ID_AND_CJBH, param), Long.class);
        return count > 0;
    }

    public Map getOne(String ddh, String cjbh){
        Map param = new HashMap();
        param.put("ddh", ddh);
        param.put("cjbh", cjbh);
        return jdbcTemplate.queryForMap(ParameterBind.bind(SQL.FIND_ORDER_BY_ID, param));
    }
}
