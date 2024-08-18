package com.nwb.cust.controller; 
import com.nwb.cust.service.TransactionService; 

public class TransactionController { 
    
    private TransactionService transactionService; 
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    } 
}
