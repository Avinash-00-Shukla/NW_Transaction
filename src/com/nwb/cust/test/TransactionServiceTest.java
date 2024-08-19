package com.nwb.cust.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.model.TransactionType;
import com.nwb.cust.service.TransactionService;

public class TransactionServiceTest {

    private TransactionService service;

    @Before
    public void setUp() {
        service = new TransactionService();
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction(21L, "NEFT", "CREDIT", 5000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS", "INR", "USER1");

        boolean result = service.addTransaction(transaction);
        assertTrue(result);

        List<Transaction> allTransactions = service.getAllTransactions();
        assertTrue(allTransactions.contains(transaction));
    }

    @Test
    public void testGetTransactionsByDateRange() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = service.getTransactionsByDateRange(startDate, endDate);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getDate().isAfter(startDate.minusSeconds(1)) && t.getDate().isBefore(endDate.plusSeconds(1)));
        }
    }

    @Test
    public void testGetTransactionsByExactAmount() {
        double amount = 5000.00;
        Transaction transaction = new Transaction(22L, "CASH", "DEBIT", amount, LocalDateTime.now(), "Test Transaction Exact Amount", "PENDING", "INR", "USER2");
        service.addTransaction(transaction);

        List<Transaction> transactions = service.getTransactionsByExactAmount(amount);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(amount, t.getAmount(), 0.001);
        }
    }

    @Test
    public void testGetTransactionsByMinAmount() {
        double minAmount = 1000.00;
        List<Transaction> transactions = service.getTransactionsByMinAmount(minAmount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getAmount() >= minAmount);
        }
    }

    @Test
    public void testGetTransactionsByMaxAmount() {
        double maxAmount = 10000.00;
        List<Transaction> transactions = service.getTransactionsByMaxAmount(maxAmount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getAmount() <= maxAmount);
        }
    }

    @Test
    public void testGetTransactionsByType() {
        TransactionType type = TransactionType.CREDIT;
        Transaction transaction = new Transaction(23L, "UPI", "CREDIT", 7500.00, LocalDateTime.now(), "Test Transaction Type", "SUCCESS", "INR", "USER3");
        service.addTransaction(transaction);

        List<Transaction> transactions = service.getTransactionsByType(type);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(type, t.getType());
        }
    }

    @Test
    public void testGetTransactionsByStatus() {
        TransactionStatus status = TransactionStatus.SUCCESS;
        List<Transaction> transactions = service.getTransactionsByStatus(status);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(status, t.getStatus());
        }
    }

    @Test
    public void testGetTransactionsByMode() {
        TransactionMode mode = TransactionMode.CASH;
        Transaction transaction = new Transaction(24L, "CASH", "DEBIT", 3000.00, LocalDateTime.now(), "Test Transaction Mode", "FAILED", "INR", "USER4");
        service.addTransaction(transaction);

        List<Transaction> transactions = service.getTransactionsByMode(mode);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(mode, t.getMode());
        }
    }

    @Test
    public void testGetTransactionsByDescription() {
        String keyword = "Demo";
        List<Transaction> transactions = service.getTransactionsByDescription(keyword);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getDescription().contains(keyword));
        }
    }

    @Test
    public void testGetTransactionById() {
        Long transactionId = 44L;
        Transaction transaction = service.getTransactionById(transactionId);
        assertNotNull(transaction);
        assertEquals(transactionId, transaction.getId());
    }

    @Test
    public void testGetTransactionsBySenderAccount() {
        String senderAccount = "USER1";
        List<Transaction> transactions = service.getTransactionsBySenderAccount(senderAccount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(senderAccount, t.getSender());
        }
    }

    @Test
    public void testGetTransactionsByReceiverAccount() {
        String receiverAccount = "USER2";
        List<Transaction> transactions = service.getTransactionsByReceiverAccount(receiverAccount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(receiverAccount, t.getReciever());
        }
    }

    @Test
    public void testGetTransactionsByCurrency() {
        String currency = "INR";
        List<Transaction> transactions = service.getTransactionsByCurrency(currency);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(currency, t.getCurrency());
        }
    }
}
