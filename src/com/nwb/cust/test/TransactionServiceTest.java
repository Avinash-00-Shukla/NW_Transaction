package com.nwb.cust.test;

import static org.junit.Assert.*;
import com.nwb.cust.model.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nwb.cust.repo.TransactionRepository;
import com.nwb.cust.service.TransactionService;

public class TransactionServiceTest {
	
	private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void testGetTransactionsByStatus_Success() {
        List<Transaction> successTransactions = transactionService.getTransactionsByStatus("SUCCESS");
        assertEquals(2, successTransactions.size());
    }

    @Test
    public void testGetTransactionsByStatus_Pending() {
        List<Transaction> pendingTransactions = transactionService.getTransactionsByStatus("PENDING");
        assertEquals(1, pendingTransactions.size());
    }

    @Test
    public void testGetTransactionsByStatus_Failed() {
        List<Transaction> failedTransactions = transactionService.getTransactionsByStatus("FAILED");
        assertEquals(1, failedTransactions.size());
    } 
	
}
