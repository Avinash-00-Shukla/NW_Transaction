package com.nwb.cust.service;  
import com.nwb.cust.repo.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository; 
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    } 
    
}
