package com.example.demo.service;

import com.example.demo.Model.Transaction;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public interface RewardService {

    Map<String, LinkedHashMap<String, Integer>> calculateRewards(List<Transaction> transactions);
}
