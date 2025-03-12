package com.example.demo.serviceImpl;

import com.example.demo.Model.Transaction;
import com.example.demo.exception.CustomException;
import com.example.demo.service.RewardService;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;

/**
 * service to calculate reward points for transactions
 */
@Service
public class RewardServiceImpl implements RewardService {


    @Override
    public Map<String, LinkedHashMap<String, Integer>> calculateRewards(List<Transaction> transactions) {
        if(Objects.isNull(transactions) || transactions.isEmpty()){
            throw new CustomException("Transaction list can't be empty");
        }
        transactions.sort(Comparator.comparing(t -> t.getTransactionDate().getMonthValue()));
        Map<String, LinkedHashMap<String, Integer>> rewardsSummary = new HashMap<>();
        Map<String, Integer> totalRewards = new HashMap<>();
        for(Transaction transaction : transactions){
            if(transaction.getTransactionAmount() < 0){
                throw new CustomException("Transaction list can't be negative: "+transaction.getTransactionAmount());
            }

            String customerName = transaction.getCustomerName();
            String month = Month.of(transaction.getTransactionDate().getMonthValue()).toString();
            int rewardsPoints = calculatePoints(transaction.getTransactionAmount());

            rewardsSummary.putIfAbsent(customerName, new LinkedHashMap<>());
            rewardsSummary.get(customerName).put(month, rewardsSummary.get(customerName).getOrDefault(month, 0)+rewardsPoints);

            totalRewards.put(customerName, totalRewards.getOrDefault(customerName, 0)+rewardsPoints);
        }
        //Adding total rewards for each customer
        for(String customer : totalRewards.keySet()){
            rewardsSummary.get(customer).put("TOTAL", totalRewards.get(customer));
        }
        return rewardsSummary;
    }

    //calculation formula as per requirement if $120 transaction then calculation should be 2*$20 + 1*$50 = 90 reward points
    private int calculatePoints(Double amount) {
        if(amount <= 0){
           return 0;
        }
        int points = 0;
        if(amount > 100){
            points = points + 2 * (int) (amount - 100);
            amount = 100.0;
        }
        if(amount > 50){
            points = points + (int) (amount - 50);
        }
        return points;
    }
}
