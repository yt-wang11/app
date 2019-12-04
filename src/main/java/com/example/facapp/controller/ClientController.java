package com.example.facapp.controller;

import com.example.facapp.model.OrderListModel;
import com.example.facapp.service.ClientService;
import com.example.facapp.service.FlowService;
import com.example.facapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姜涛
 * @create 2018-04-17 13:42
 * @desc 客户控制层
 **/
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public List list(Long cjbh) {
        List result = new ArrayList();
        try {
            result = clientService.getList(cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/allList")
    public List AllList(Long cjbh) {
        List result = new ArrayList();
        try {
            result = clientService.getAllList(cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/delete")
    @SuppressWarnings("all")
    public String delete(String xhs) {
        boolean result = false;
        try {
            if (xhs.lastIndexOf(",") == xhs.length()-1){
                xhs = xhs.substring(0, xhs.length()-1);
            }
            result = clientService.delete(xhs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @PostMapping(value = "/insert")
    public String insert(Long cjbh, String khmc, String khdz, String lxfs, String hzsj) {
        String result = "error";
        try {
            if (clientService.insert(cjbh, khmc, khdz, lxfs, Timestamp.valueOf(hzsj + " 00:00:00"))) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/update")
    public String update(Long xh, Long cjbh, String khmc, String khdz, String lxfs, String hzsj) {
        String result = "error";
        try {
            if (clientService.update(xh, cjbh, khmc, khdz, lxfs, Timestamp.valueOf(hzsj + " 00:00:00"))) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/show")
    public Map show(Long xh) {
        Map result = new HashMap();
        try {
            result = clientService.show(xh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/showOrder")
    public List<OrderListModel> showOrder(String cjbh, Long khbh) {
        //展示客户所有的合作订单
        List<OrderListModel> result = new ArrayList<>();
        try {
            result = orderService.findAllByClient(Long.parseLong(cjbh), khbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
