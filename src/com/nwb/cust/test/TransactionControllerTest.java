package com.nwb.cust.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.nwb.cust.model.*;
import com.nwb.cust.controller.TransactionController;
import com.nwb.cust.repo.TransactionRepository;
import com.nwb.cust.service.TransactionService;

public class TransactionControllerTest {
	
	 private TransactionController controller;
	    private TransactionService service;
	    private TransactionRepository repository;

	    @Before
	    public void setUp() {
	        repository = new TransactionRepository();
	        service = new TransactionService(repository);
	        controller = new TransactionController(service);
	    }

	    @Test
	    public void testGetTransactionsByStatus_Success() {
	        List<Transaction> successTransactions = controller.getTransactionsByStatus("SUCCESS");
	        assertEquals(2, successTransactions.size());
	    }

	    @Test
	    public void testGetTransactionsByStatus_Pending() {
	        List<Transaction> pendingTransactions = controller.getTransactionsByStatus("PENDING");
	        assertEquals(1, pendingTransactions.size());
	    }

	    @Test
	    public void testGetTransactionsByStatus_Failed() {
	        List<Transaction> failedTransactions = controller.getTransactionsByStatus("FAILED");
	        assertEquals(1, failedTransactions.size());
	    }
}
