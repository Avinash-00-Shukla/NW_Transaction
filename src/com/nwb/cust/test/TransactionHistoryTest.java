package com.nwb.cust.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.service.TransactionHistory;

public class TransactionHistoryTest {

    private TransactionHistory transactionHistory;

    @Before
    public void setUp() {
        transactionHistory = new TransactionHistory();

        // Adding some sample transactions
        transactionHistory.addTransaction(new Transaction(1L, "UPI", 1500.00, LocalDateTime.now().minusDays(2), "First Transaction", TransactionStatus.SUCCESS));
        transactionHistory.addTransaction(new Transaction(2L, "NEFT", 2500.00, LocalDateTime.now().minusDays(4), "Second Transaction", TransactionStatus.PENDING));
        transactionHistory.addTransaction(new Transaction(3L, "CASH", 500.00, LocalDateTime.now().minusDays(1), "Third Transaction", TransactionStatus.SUCCESS));
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction(4L, "CHEQUE", 2000.00, LocalDateTime.now(), "Fourth Transaction", TransactionStatus.CANCELLED);

        transactionHistory.addTransaction(transaction);
        
        List<Transaction> allTransactions = transactionHistory.getAllTransactions();
        assertEquals(4, allTransactions.size()); // Should have 4 transactions now
        assertTrue(allTransactions.contains(transaction));
    }

    @Test
    public void testGetTransactionsByDateRange() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);
        LocalDateTime endDate = LocalDateTime.now().minusDays(1);

        List<Transaction> transactions = transactionHistory.getTransactionsByDateRange(startDate, endDate);
        
        assertNotNull(transactions);
        assertEquals(2, transactions.size()); // Transactions within the specified date range
    }

    @Test
    public void testGetTransactionsByStatus() {
        List<Transaction> successTransactions = transactionHistory.getTransactionsByStatus(TransactionStatus.SUCCESS);
        
        assertNotNull(successTransactions);
        assertEquals(2, successTransactions.size()); // Should return 2 successful transactions
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = transactionHistory.getTransactionById(2L);

        assertNotNull(transaction);
        assertEquals("Second Transaction", transaction.getDescription());

        Transaction nonExistentTransaction = transactionHistory.getTransactionById(999L);
        assertNull(nonExistentTransaction); // Should return null for non-existent ID
    }

    @Test
    public void testSearchTransactionsByAmount() {
        List<Transaction> transactions = transactionHistory.searchTransactionsByAmount(1500.00);

        assertNotNull(transactions);
        assertEquals(1, transactions.size()); // Should return 1 transaction with amount 1500.00
        assertEquals("First Transaction", transactions.get(0).getDescription());
    }

    @Test
    public void testSearchTransactionsByDescription() {
        List<Transaction> transactions = transactionHistory.searchTransactionsByDescription("Transaction");

        assertNotNull(transactions);
        assertEquals(3, transactions.size()); // All transactions contain the word "Transaction" in the description

        List<Transaction> noMatchTransactions = transactionHistory.searchTransactionsByDescription("Non-existent");
        assertTrue(noMatchTransactions.isEmpty()); // Should return an empty list
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> allTransactions = transactionHistory.getAllTransactions();

        assertNotNull(allTransactions);
        assertEquals(3, allTransactions.size()); // Initially added 3 transactions in setUp
    }
}
