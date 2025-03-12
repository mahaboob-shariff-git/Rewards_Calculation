package com.example.demo.controller;

import com.example.demo.Model.Transaction;
import com.example.demo.service.RewardService;
import com.example.demo.serviceImpl.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RewardControllerTest {

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRewardTest_Success(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1l, "Shariff", 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(2l, "Suresh", 75.0, LocalDate.of(2024, 8, 30))
        );
        Map<String, LinkedHashMap<String, Integer>> rewards = Map.of(
                "Shariff", new LinkedHashMap<>(Map.of("JANUARY", 90, "TOTAL", 90)),
                "Suresh", new LinkedHashMap<>(Map.of("AUGUST", 25, "TOTAL", 25))
                );
        when(rewardService.calculateRewards(transactions)).thenReturn(rewards);
        ResponseEntity<?> result = rewardController.getRewards();
        assertEquals(HttpStatus.valueOf(200), result.getStatusCode());
    }

}
