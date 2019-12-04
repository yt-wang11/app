package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.FlowModel;
import com.example.facapp.model.SearchResult;
import com.example.facapp.repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 姜涛
 * @create 2018-04-17 13:44
 * @desc 流程业务逻辑层
 **/
@Service
public class FlowService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FlowRepository flowRepository;

    public List getList(Long cjbh) {
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        return jdbcTemplate.queryForList(ParameterBind.bind(SQL.GET_ALL_FLOW_BY_CJBH, param));
    }

    public List<SearchResult> getFlowList(String cjbh) {
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        return jdbcTemplate.query(ParameterBind.bind(SQL.GET_FLOW_BY_CJBH, param), new BeanPropertyRowMapper<>(SearchResult.class));
    }

    public boolean delete(String xhs) {
        Map param = new HashMap();
        param.put("xhs", xhs);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_FLOW_BY_XHS, param));
        return update!=0;
    }

    public boolean insert(Long cjbh, String bzjb, String bzmc, String bzts) {
        int update = jdbcTemplate.update(SQL.INSERT_FLOW, t ->{
            t.setLong(1, cjbh);
            t.setString(2, bzjb);
            t.setString(3, bzmc);
            t.setString(4, bzts);
        });
        return update!=0;
    }

    public boolean update(Long xh, Long cjbh, String bzjb, String bzmc, String bzts) {
        FlowModel one = flowRepository.getOne(xh);
        one.setCjbh(cjbh);
        one.setBzjb(bzjb);
        one.setBzmc(bzmc);
        one.setBzts(bzts);
        FlowModel flowModel = flowRepository.saveAndFlush(one);
        return Objects.nonNull(flowModel);
    }

    public Map show(Long xh) {
        Map param = new HashMap();
        param.put("xh", xh);
        return jdbcTemplate.queryForMap(ParameterBind.bind(SQL.SHOW_FLOW_BY_XH, param));
    }

    public boolean exist(String bzjb, Long cjbh) {
        Map param = new HashMap();
        param.put("bzjb", bzjb);
        param.put("cjbh", cjbh);
        Long count = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.EXIST_BY_BZJB_AND_CJBH, param), Long.class);
        return count > 0;
    }
}
