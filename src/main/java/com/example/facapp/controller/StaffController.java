package com.example.facapp.controller;

import com.example.facapp.dto.StaffDto;
import com.example.facapp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/staff")
public class StaffController {

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
    public String delete(String ids) {
        boolean result = false;
        try {
            result = staffService.delete(ids);
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

    @GetMapping(value = "/export")
    public void export(String ids, HttpServletResponse response) {
        try {
            staffService.export(ids, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/exist")
    public Map exist(Long ddh, Long cjbh) {
        Map result = new HashMap();
        result.put("valid", true);
        try {
            // 待写代码
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
