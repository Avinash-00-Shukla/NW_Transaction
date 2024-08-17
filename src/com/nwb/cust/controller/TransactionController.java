package com.nwb.cust.controller;

import com.nwb.cust.service.TransactionService;

import java.util.List;

import com.nwb.cust.model.*;

public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionService.getTransactionsByStatus(status);
    
  }
    }
