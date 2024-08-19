package com.nwb.cust.test; 
import static org.junit.Assert.*; 
import java.time.LocalDateTime;
import java.util.List; 
import org.junit.Before;
import org.junit.Test; 
import com.nwb.cust.model.Transaction; 
import com.nwb.cust.model.TransactionStatus; 
import com.nwb.cust.service.TransactionService;

public class TransactionServiceTest {

    private TransactionService service;

    @Before
    public void setUp() {
        service = new TransactionService();
    }

    @Test
    public void testAddTransaction() {
        // Create a transaction using strings for mode, type, and status
        Transaction transaction = new Transaction(21L, "NEFT", "CREDIT", 5000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS","USD","USER1");

        boolean result = service.addTransaction(transaction);
        assertTrue(result);

        List<Transaction> allTransactions = service.getAllTransactions();
        assertTrue(allTransactions.contains(transaction));
    } 
}
