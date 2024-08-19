package com.nwb.cust.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.repo.TransactionRepository;
import com.nwb.cust.service.TransactionService;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository(); // Using repository with dummy data
        transactionService = new TransactionService();
        
        // Populate transaction history with data from repository
        for (Transaction transaction : transactionRepository.transactions) {
            transactionService.addTransaction(transaction);
        }
    }

    @Test
    public void testViewTransactionHistory() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        TransactionStatus status = TransactionStatus.SUCCESS;

        String result = transactionService.viewTransactionHistory(startDate, endDate, status);
        assertNotNull(result);
        assertTrue(result.contains("For SUCCESS Testing"));
        assertFalse(result.contains("For CANCELLED Testing"));
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction(9L, "NEFT", 5000.00, LocalDateTime.now(), "Test Transaction", TransactionStatus.SUCCESS);

        transactionService.addTransaction(transaction);

        List<Transaction> allTransactions = transactionService.getAllTransactions();
        assertTrue(allTransactions.contains(transaction));
    }

    @Test
    public void testSearchTransactionsByAmount() {
        double amount = 5000.00;
        Transaction transaction = new Transaction(9L, "NEFT", amount, LocalDateTime.now(), "Test Transaction", TransactionStatus.SUCCESS);
        transactionService.addTransaction(transaction);

        String result = transactionService.searchTransactionsByAmount(amount);
        assertNotNull(result);
        assertTrue(result.contains("Test Transaction"));
    }

    @Test
    public void testSearchTransactionsByDescription() {
        String description = "SUCCESS Testing";
        
        String result = transactionService.searchTransactionsByDescription(description);
        assertNotNull(result);
        assertTrue(result.contains("For SUCCESS Testing"));
    }

    @Test
    public void testGetTransactionDetails() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction(transactionId, "NEFT", 5000.00, LocalDateTime.now(), "Test Transaction", TransactionStatus.SUCCESS);
        transactionService.addTransaction(transaction);

        String result = transactionService.getTransactionDetails(transactionId);
        assertNotNull(result);
        assertTrue(result.contains("Test Transaction"));
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> result = transactionService.getAllTransactions();
        assertNotNull(result);
        assertEquals(8, result.size()); // Repository initially contains 8 transactions
    }
}
