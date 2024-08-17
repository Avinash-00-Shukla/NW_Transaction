package com.nwb.cust.service;

import java.util.List;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.repo.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }
}
