package com.example.facapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerRepositoryTest {
    @Autowired
    private WorkerRepository workerRepository;

    @Test
    public void test1(){
        System.out.println(workerRepository.findAll());
    }
}