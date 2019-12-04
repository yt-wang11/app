package com.example.facapp.controller;

import com.example.facapp.dto.StaffDto;
import com.example.facapp.service.OrderService;
import com.example.facapp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/list")
    public Map list(int limit, int offset, String search) {
        Map result = new HashMap();
        result.put("total", staffService.count(search));
        result.put("rows", staffService.findAllByPage(limit, offset, search));
        return result;
    }

    @GetMapping(value = "/delete")
    @SuppressWarnings("all")
    public String delete(String ids) {
        boolean result = false;
        try {
            if (ids.lastIndexOf(",") == ids.length()-1){
                ids = ids.substring(0, ids.length()-1);
            }
            result = orderService.delete(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @PostMapping(value = "/insert")
    public String insert(StaffDto staffDto) {
        String result = "error";
        try {
            if (staffService.insert(staffDto)) {
                result = "success";
            }
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
    public Map show(Integer id) {
        Map result = new HashMap();
        try {
            result = staffService.show(id);
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
