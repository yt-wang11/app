package com.example.facapp.controller;

import com.example.facapp.model.WorkerModel;
import com.example.facapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姜涛
 * @create 2018-04-14 16:46
 * @desc 订单controller
 **/

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @RequestMapping(value = "/list")
    public List<WorkerModel> list(String cjbh) {
        List<WorkerModel> workerModelList = workerService.findAllByPage(cjbh);
        return workerService.compute(workerModelList);
    }

    @RequestMapping(value = "/showMess")
    public List<WorkerModel> showMess(String cjbh, String grxm) {
        return workerService.findAllByCjAndXm(cjbh, grxm);
    }

    @GetMapping(value = "/delete")
    @SuppressWarnings("all")
    public String delete(String names, String cjbh) {
        boolean result = false;
        try {
            if (names.lastIndexOf(",") == names.length()-1){
                names = names.substring(0, names.length()-1);
            }
            result = workerService.delete(names, cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @GetMapping(value = "/deleteOne")
    @SuppressWarnings("all")
    public String deleteOne(String xh, Long cjbh) {
        boolean result = false;
        try {
            result = workerService.deleteOne(xh, cjbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result?"true":"false";
    }

    @PostMapping(value = "/insert")
    public String insert(Long cjbh, String grxm, String lxfs, Double yzje,  String yzsj) {
        String result = "error";
        try {
            if (workerService.insert(cjbh, grxm, lxfs, yzje, Timestamp.valueOf(yzsj + " 00:00:00"))) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/advance")
    public Map advance(Long xh) {
        Map result = new HashMap();
        try {
            result = workerService.advance(xh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/calc")
    public String calc(Long cjbh, String grxm) {
        String result = "error";
        try {
            if (workerService.calc(cjbh, grxm)) result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
