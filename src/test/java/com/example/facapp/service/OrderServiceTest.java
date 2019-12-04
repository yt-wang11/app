package com.example.facapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        orderService.findAllByPage(0, 10, "20108041401", "1000");
        System.out.println();
    }

    @Test
    public void test1() {
        orderService.findAllByPage(0, 10, "", "1000");
        System.out.println();
    }

    @Test
    public void deleteTest() {
        orderService.delete("1,2,3");
        System.out.println();
    }
}