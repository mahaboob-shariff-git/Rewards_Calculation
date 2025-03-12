package com.example.demo.controller;

import com.example.demo.Model.Transaction;
import com.example.demo.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/*
   REST controller for retrieving rewards points
 */
@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

//    API to get rewards points for transactions.
//    which returns rewards points grouped by customer & month
    @GetMapping
    public ResponseEntity<?> getRewards(){
        try {
            //Creating custom input request as per the requirement for which to find the reward points
            List<Transaction> transactions = Arrays.asList(
                    new Transaction(1l, "Shariff", 120.0, LocalDate.of(2025, 1, 15)),
                    new Transaction(2l, "Shariff", 170.0, LocalDate.of(2024, 3, 19)),
                    new Transaction(3l, "Shariff", 200.0, LocalDate.of(2024, 6, 5)),

                    new Transaction(4l, "Suresh", 20.0, LocalDate.of(2025, 1, 10)),
                    new Transaction(5l, "Suresh", 60.0, LocalDate.of(2024, 4, 10)),
                    new Transaction(6l, "Suresh", 120.0, LocalDate.of(2024, 8, 30))
            );
            Map<String, LinkedHashMap<String, Integer>> resultMap = rewardService.calculateRewards(transactions);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
