package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.model.OrderListModel;
import com.example.facapp.model.OrderModel;
import com.example.facapp.model.WorkerModel;
import com.example.facapp.repository.ClientRepository;
import com.example.facapp.repository.FlowRepository;
import com.example.facapp.repository.WorkerRepository;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 姜涛
 * @create 2018-04-14 14:15
 * @desc 工人列表service
 **/
@Service
public class WorkerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WorkerRepository workerRepository;

    /*分页查询订单列表*/
    public List<WorkerModel> findAllByPage(String cjbh){
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        return jdbcTemplate.query(ParameterBind.bind(SQL.FIND_WORKER_BY_CJ, param), new BeanPropertyRowMapper<>(WorkerModel.class));
    }

    /*分页查询订单列表*/
    public List<WorkerModel> findAllByCjAndXm(String cjbh, String grxm){
        Map param = new HashMap();
        param.put("cjbh", cjbh);
        param.put("grxm", grxm);
        return jdbcTemplate.query(ParameterBind.bind(SQL.FIND_WORKER_BY_CJ_AND_GRXM, param), new BeanPropertyRowMapper<>(WorkerModel.class));
    }

    /*分页查询订单列表*/
    public List<WorkerModel> compute(List<WorkerModel> modelList){
        List<WorkerModel> resultList = new ArrayList<>();
        Map<String, List<WorkerModel>> collect = modelList.stream().collect(Collectors.groupingBy(WorkerModel::getGrxm));
        for (String name : collect.keySet()) {
            Double totalMoney = 0.0;
            List<WorkerModel> list = collect.get(name);
            for (WorkerModel workerModel : list) {
                totalMoney += workerModel.getYzje();
            }
            WorkerModel resultModel = list.get(0);
            resultModel.setYzje(totalMoney);
            resultList.add(resultModel);
        }
        return resultList;
    }


    public boolean delete(String names, String cjbh){
        Map param = new HashMap();
        param.put("names", names);
        param.put("cjbh", cjbh);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_WORKER_BY_NAMES, param));
        return update!=0;
    }

    public boolean deleteOne(String xh, Long cjbh){
        Map param = new HashMap();
        param.put("xh", xh);
        if (this.isLast(xh, cjbh)){
            WorkerModel one = workerRepository.getOne(Long.parseLong(xh));
            one.setYzje(0.0);
            WorkerModel save = workerRepository.save(one);
            return Objects.nonNull(save);
        }
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_WORKER_BY_XH, param));
        return update!=0;
    }

    /*判断删除的是不是最后一个*/
    private boolean isLast(String xh, Long cjbh) {
        Map param = new HashMap();
        param.put("xh", xh);
        String name = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.GET_WORKER_NAME, param), String.class);
        param.put("grxm", name);
        param.put("cjbh", cjbh);
        int num = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.GET_WORKER_NUM, param), Integer.class);
        return num == 1;
    }

    @SuppressWarnings("all")
    public boolean insert(Long cjbh, String grxm, String lxfs, Double yzje,  Timestamp yzsj){
        if (this.existOld(grxm, cjbh)){
            WorkerModel worker = workerRepository.findFirstByGrxmAndCjbh(grxm, cjbh);
            worker.setLxfs(lxfs);
            worker.setYzje(yzje);
            worker.setYzsj(yzsj);
            WorkerModel save = workerRepository.save(worker);
            return Objects.nonNull(save);
        }
        int update = jdbcTemplate.update(SQL.INSERT_WORKER, t ->{
            t.setLong(1, cjbh);
            t.setString(2, grxm);
            t.setString(3, lxfs);
            t.setDouble(4, yzje);
            t.setTimestamp(5, yzsj);
        });
        return update!=0;
    }

    /*判断是否存在删除中置为0.0的数据*/
    private boolean existOld(String grxm, Long cjbh){
        Map param = new HashMap();
        param.put("grxm", grxm);
        param.put("cjbh", cjbh);
        int num = jdbcTemplate.queryForObject(ParameterBind.bind(SQL.GET_WORKER_NUM, param), Integer.class);
        if (num == 1){
            WorkerModel worker = workerRepository.findFirstByGrxmAndCjbh(grxm, cjbh);
            if (worker.getYzje() == 0.0)
                return true;
        }

        return false;
    }

    public Map advance(Long xh) {
        WorkerModel one = workerRepository.getOne(xh);
        Map result = new HashMap();
        result.put("grxm", one.getGrxm());
        result.put("lxfs", one.getLxfs());
        return result;
    }

    public boolean calc(Long cjbh, String grxm) {
        WorkerModel one = workerRepository.findFirstByGrxmAndCjbh(grxm, cjbh);
        Integer count = workerRepository.deleteAllByCjbhAndAndGrxm(cjbh, grxm);
        if (count > 0){
            one.setYzje(0.0);
            WorkerModel workerModel = workerRepository.saveAndFlush(one);
            if (Objects.nonNull(workerModel)) return true;
        }
        return true;
    }
}
