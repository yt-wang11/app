package com.example.facapp.controller;

import com.example.facapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 姜涛
 * @create 2018-04-14 16:46
 * @desc 订单controller
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/list")
    public Map list(int limit, int offset, String search, String cjbh) {
        Map result = new HashMap();
        result.put("total", orderService.count(cjbh, search));
        result.put("rows", orderService.findAllByPage(limit, offset, search, cjbh));
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
            result = orderService.delete(xhs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @PostMapping(value = "/insert")
    public String insert(Long cjbh, Long khbh, String ddh, String bzjb, String ddsj) {
        String result = "error";
        try {
            if (orderService.insert(cjbh, khbh, ddh, bzjb, Timestamp.valueOf(ddsj + " 00:00:00"))) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/update")
    public String update(Long xh, Long cjbh, Long khbh, String ddh, String bzjb, String ddsj) {
        String result = "error";
        try {
            if (orderService.update(xh, cjbh, khbh, ddh, bzjb, Timestamp.valueOf(ddsj + " 00:00:00"))) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/show")
    public Map show(Long xh) {
        Map result = new HashMap();
        try {
            result = orderService.show(xh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/exist")
    public Map exist(Long ddh, Long cjbh) {
        Map result = new HashMap();
        result.put("valid", true);
        try {
            if (orderService.exist(ddh, cjbh)) result.put("valid", false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
