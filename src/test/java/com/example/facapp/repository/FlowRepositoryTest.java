package com.example.facapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowRepositoryTest {
    @Autowired
    private FlowRepository flowRepository;

    @Test
    public void test1(){
        System.out.println(flowRepository.findAll());
        System.out.println();
    }

    @Test
    public void test(){
        System.out.println(flowRepository.findFirstByCjbhAndBzjb(1001L, "高级"));
        System.out.println();
    }
}