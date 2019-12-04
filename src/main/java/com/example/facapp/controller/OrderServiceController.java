package com.example.facapp.controller;

import com.example.facapp.service.BeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class OrderServiceController {

    @Autowired
    private BeginService beginService;

    @PostMapping("/begin")
    public String service(String str, String cjbh) {
        String result = "error";
        try {
            /*拿到扫描枪扫描进来的字符串进行处理，取最后一个字符串为服务级别，前面为订单号*/
            String orderId = str.substring(0, str.length()-1);
            String bzjb = str.substring(str.length()-1, str.length());
            if (beginService.orderExist(cjbh, orderId) && beginService.flowExist(cjbh, bzjb)){
                Long orderXh = beginService.getOrderXh(cjbh, orderId);
                if (beginService.update(orderXh, bzjb)) result = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
