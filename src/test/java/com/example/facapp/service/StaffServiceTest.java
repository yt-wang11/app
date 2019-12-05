package com.example.facapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *  * @description: description
 *  * @author: jiangtao
 *  * @date: 2019-12-05 21:26
 *  * @modify: modify
 *  
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void findAllByRemind() {
        List<Map<String, String>> list = staffService.findAllByRemind(10, 1, "3,11,13,22,123,");
        System.out.println(list);
    }
}