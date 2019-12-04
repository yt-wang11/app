package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.ClientModel;
import com.example.facapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
public class ClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientRepository clientRepository;

    public List getList(Long cjbh) {
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        return jdbcTemplate.queryForList(ParameterBind.bind(SQL.GET_CLIENT_BY_CJBH, param));
    }

    public List getAllList(Long cjbh) {
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        return jdbcTemplate.queryForList(ParameterBind.bind(SQL.GET_ALL_CLIENT_BY_CJBH, param));
    }

    public boolean delete(String xhs) {
        Map param = new HashMap();
        param.put("xhs", xhs);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_CLIENT_BY_XHS, param));
        for (String xh : xhs.split(",")) {
            orderService.deleteByKhbh(xh);
        }
        return update!=0;
    }

    public boolean insert(Long cjbh, String khmc, String khdz, String lxfs, Timestamp hzsj) {
        int update = jdbcTemplate.update(SQL.INSERT_CLIENT, t ->{
            t.setLong(1, cjbh);
            t.setString(2, khmc);
            t.setString(3, khdz);
            t.setTimestamp(4, hzsj);
            t.setString(5, lxfs);
        });
        return update!=0;
    }

    @SuppressWarnings("all")
    public boolean update(Long xh, Long cjbh, String khmc, String khdz, String lxfs, Timestamp hzsj){
        ClientModel one = clientRepository.getOne(xh);
        one.setCjbh(cjbh);
        one.setKhmc(khmc);
        one.setKhdz(khdz);
        one.setLxfs(lxfs);
        one.setHzsj(hzsj);
        ClientModel clientModel = clientRepository.saveAndFlush(one);
        /*int update = jdbcTemplate.update(SQL.UPDATE_CLIENT + "WHERE xh=" + xh, t ->{
            t.setLong(1, cjbh);
            t.setString(2, khmc);
            t.setString(3, khdz);
            t.setTimestamp(4, hzsj);
            t.setString(5, lxfs);
        });*/
        return !Objects.equals(clientModel, null);
    }

    public Map<String, Object> show(Long xh){
        Map param = new HashMap();
        param.put("xh", xh);
        return jdbcTemplate.queryForMap(ParameterBind.bind(SQL.SHOW_CLIENT_BY_XH, param));
    }
}
