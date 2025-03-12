package com.example.demo.serviceImpl;

import com.example.demo.Model.Transaction;
import com.example.demo.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RewardServiceImplTest {

    public final RewardServiceImpl rewardService = new RewardServiceImpl();

    @Test
    void testCalculateRewards_Success(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1l, "Shariff", 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(2l, "Suresh", 75.0, LocalDate.of(2024, 8, 30))
        );

        Map<String, LinkedHashMap<String, Integer>> result = rewardService.calculateRewards(transactions);
        assertEquals(90, result.get("Shariff").get("JANUARY"));
        assertEquals(25, result.get("Suresh").get("AUGUST"));
    }

    @Test
    void testCalculateRewards_Empty(){
        assertThrows(CustomException.class, () -> rewardService.calculateRewards(List.of()));
    }

    @Test
    void testCalculateRewards_NegativeTransactionAmount(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1l, "Shariff", -100.0, LocalDate.of(2025, 1, 15))
        );
        assertThrows(CustomException.class, () -> rewardService.calculateRewards(transactions));
    }


}
