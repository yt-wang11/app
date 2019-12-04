package com.example.facapp.controller;

import com.example.facapp.model.SearchResult;
import com.example.facapp.service.AccountService;
import com.example.facapp.service.FlowService;
import com.example.facapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 姜涛
 * @create 2018-04-17 13:42
 * @desc 搜索控制层
 **/
@RestController
public class SearchController {

    @Autowired
    private FlowService flowService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/search")
    public List search(String orderId, String cjbh) {
        List<SearchResult> flowList = new ArrayList<>();
        try {
            if (orderService.exist(Long.parseLong(orderId), Long.parseLong(cjbh))){
                flowList = flowService.getFlowList(cjbh);
                Map data = orderService.getOne(orderId, cjbh);
                for (SearchResult searchResult : flowList) {
                    if (searchResult.getBzjb().equals(data.get("bzjb"))){
                        searchResult.setNow(1);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flowList;
    }


}
