package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.dto.StaffDto;
import com.example.facapp.model.OrderListModel;
import com.example.facapp.model.OrderModel;
import com.example.facapp.model.StaffModel;
import com.example.facapp.repository.ClientRepository;
import com.example.facapp.repository.FlowRepository;
import com.example.facapp.repository.OrderRepository;
import com.example.facapp.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

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
    public List<StaffDto> findAllByPage(int limit, int offset, String seach){
        List<StaffModel> staffModels;
        if (!StringUtils.isEmpty(seach)) {
            if (isNumericZidai(seach)) {
                staffModels = staffRepository.findAllById(Integer.valueOf(seach));
            } else {
                staffModels = staffRepository.findByNameLike("%" + seach + "%");
            }
        } else {
            Map param = new HashMap();
            param.put("limit", limit);
            param.put("offset", offset);
            staffModels = jdbcTemplate.query(ParameterBind.bind(SQL.FIND_ORDER_BY_CJ, param), new BeanPropertyRowMapper<>(StaffModel.class));
        }

        List<StaffDto> result = new ArrayList<>();
        for (StaffModel staffModel : staffModels) {
            StaffDto staffDto = new StaffDto();
            staffDto.setId(staffModel.getId());
            staffDto.setName(staffModel.getName());
            staffDto.setAge(staffModel.getAge());
            staffDto.setSex(staffModel.getSex());
            staffDto.setWorkdepartment(staffModel.getWorkdepartment());
            staffDto.setIdcard(staffModel.getIdcard());
            staffDto.setEducation(staffModel.getEducation());
            staffDto.setTitle(staffModel.getTitle());
            staffDto.setEntrytime(staffModel.getEntrytime().toString());
            staffDto.setContractlife(staffModel.getContractlife());
            staffDto.setWorkchangerecord(staffModel.getWorkchangerecord());
            staffDto.setRewardsandpunishmentrecords(staffModel.getRewardsandpunishmentrecords());
            staffDto.setRankevaluationrecord(staffModel.getRankevaluationrecord());
            result.add(staffDto);
        }


        return result;
    }

    public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
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

    public Long count(String search){
        Map param = new HashMap();
        if (!StringUtils.isEmpty(search)){
            param.put("Name", search);
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT_BY_ID, param), Long.class);
        }else {
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT, param), Long.class);
        }
    }

    public boolean delete(String xhs){
        Map param = new HashMap();
        param.put("xhs", xhs);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_ORDER_BY_XHS, param));
        return update!=0;
    }

    public void deleteByKhbh(String khbh){
        Map param = new HashMap();
        param.put("khbh", khbh);
        jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_ORDER_BY_KHBH, param));
    }

    @SuppressWarnings("all")
    public boolean insert(StaffDto staffDto){
        StaffModel staffModel = new StaffModel();
        staffModel.setId(staffDto.getId());
        staffModel.setName(staffDto.getName());
        staffModel.setAge(staffDto.getAge());
        staffModel.setSex(staffDto.getSex());
        staffModel.setWorkdepartment(staffDto.getWorkdepartment());
        staffModel.setIdcard(staffDto.getIdcard());
        staffModel.setEducation(staffDto.getEducation());
        staffModel.setTitle(staffDto.getTitle());
        staffModel.setEntrytime(Timestamp.valueOf(staffDto.getEntrytime() + " 00:00:00"));
        staffModel.setContractlife(staffDto.getContractlife());
        staffModel.setWorkchangerecord(staffDto.getWorkchangerecord());
        staffModel.setRewardsandpunishmentrecords(staffDto.getRewardsandpunishmentrecords());
        staffModel.setRankevaluationrecord(staffDto.getRankevaluationrecord());
        StaffModel model = staffRepository.saveAndFlush(staffModel);

        return Objects.nonNull(model);
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

    public Map<String, Object> show(Integer id){
        Map param = new HashMap();
        param.put("id", id);
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
