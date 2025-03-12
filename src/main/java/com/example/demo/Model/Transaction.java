package com.example.demo.Model;


import java.time.LocalDate;

public class Transaction {

    private Long id;
    private String customerName;
    private Double transactionAmount;
    private LocalDate transactionDate;

    public Transaction(Long id, String customerName, Double transactionAmount, LocalDate transactionDate) {
        this.id = id;
        this.customerName = customerName;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
