package com.example.facapp.controller;

import com.example.facapp.service.ClientService;
import com.example.facapp.service.FlowService;
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
 * @desc 流程控制层
 **/
@RestController
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @RequestMapping("/list")
    public List list(Long cjbh) {
        List result = new ArrayList();
        try {
            result = flowService.getList(cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/delete")
    @SuppressWarnings("all")
    public String delete(String xhs) {
        boolean result = false;
        try {
            if (xhs.lastIndexOf(",") == xhs.length()-1){
                xhs = xhs.substring(0, xhs.length()-1);
            }
            result = flowService.delete(xhs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @PostMapping(value = "/insert")
    public String insert(Long cjbh, String bzjb, String bzmc, String bzts) {
        String result = "error";
        try {
            if (flowService.insert(cjbh, bzjb, bzmc, bzts)) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/update")
    public String update(Long xh, Long cjbh, String bzjb, String bzmc, String bzts) {
        String result = "error";
        try {
            if (flowService.update(xh, cjbh, bzjb, bzmc, bzts)) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/show")
    public Map show(Long xh) {
        Map result = new HashMap();
        try {
            result = flowService.show(xh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/exist")
    public Map exist(String bzjb, Long cjbh) {
        Map result = new HashMap();
        result.put("valid", true);
        try {
            if (flowService.exist(bzjb, cjbh)) result.put("valid", false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
