package com.example.facapp.repository;

import com.example.facapp.model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test1(){
        Page<OrderModel> all = orderRepository.findAll(PageRequest.of(1, 10));
    }

    @Test
    public void test(){
        List<OrderModel> one = orderRepository.findAllByDdhAndCjbh("20108041401", 1000L);
    }
}