package com.nwb.cust.test; 
import static org.junit.Assert.*; 
import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionType;
import com.nwb.cust.repo.TransactionFiltersImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TransactionFilterImpTest {

    private static Connection connection;
    private static TransactionFiltersImpl transactionFilters;

    @Before
    public void setup() throws ClassNotFoundException, SQLException {
        // Initialize the database connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "system";
        String PASS = "sys123";
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        // Ensure the test class uses the same connection
        transactionFilters = new TransactionFiltersImpl();
    }

    @After
    public void tearDown() throws SQLException {
        // Close the connection after all tests are done
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testGetTransactionsByDateRange() throws SQLException, ClassNotFoundException {
        LocalDateTime startDate = LocalDateTime.of(2024, 8, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 8, 31, 23, 59);

        List<Transaction> transactions = transactionFilters.getTransactionsByDateRange(startDate, endDate);

        assertNotNull(transactions);
        //assertEquals(10, transactions.size());  // Expected 10 transactions in the date range
        assertEquals("TXN001", transactions.get(0).getTransactionId());
    }

//    @Test
//    public void testGetTransactionsByAmount() throws SQLException, ClassNotFoundException {
//        double amount = 500.00;
//
//        List<Transaction> transactions = transactionFilters.getTransactionsByAmount(amount);
//
//        assertNotNull(transactions);
//        assertEquals(2, transactions.size());  // Assuming there are 2 transactions with this amount
//    }

    @Test
    public void testGetTransactionsByMinAmount() throws SQLException, ClassNotFoundException {
        double minAmount = 1000.00;

        List<Transaction> transactions = transactionFilters.getTransactionsByMinAmount(minAmount);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getAmount() >= minAmount));
    }

    @Test
    public void testGetTransactionsByMaxAmount() throws SQLException, ClassNotFoundException {
        double maxAmount = 1000.00;

        List<Transaction> transactions = transactionFilters.getTransactionsByMaxAmount(maxAmount);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getAmount() <= maxAmount));
    }

    @Test
    public void testGetTransactionsByType() throws SQLException, ClassNotFoundException {
        TransactionType type = TransactionType.CREDIT;

        List<Transaction> transactions = transactionFilters.getTransactionsByType(type);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getType() == type));
    }
    
  
    @Test
    public void testGetTransactionsByMode() throws SQLException, ClassNotFoundException {
        TransactionMode mode = TransactionMode.CREDIT_CARD;

        List<Transaction> transactions = transactionFilters.getTransactionsByMode(mode);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getMode() == mode));
    }

    @Test
    public void testGetTransactionsByDescription() throws SQLException, ClassNotFoundException {
        String keyword = "services";

        List<Transaction> transactions = transactionFilters.getTransactionsByDescription(keyword);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getDescription().contains(keyword)));
    }

    @Test
    public void testGetTransactionByTransactionId() throws SQLException, ClassNotFoundException {
        String transactionId = "TXN001";

        List<Transaction> transactions = transactionFilters.getTransactionByTransactionId(transactionId);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(transactionId, transactions.get(0).getTransactionId());
    }

    @Test
    public void testGetTransactionsBySenderAccount() throws SQLException, ClassNotFoundException {
        String senderAccount = "ACC001";

        List<Transaction> transactions = transactionFilters.getTransactionsBySenderAccount(senderAccount);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getSenderAccount().equals(senderAccount)));
    }

    @Test
    public void testGetTransactionsByReceiverAccount() throws SQLException, ClassNotFoundException {
        String receiverAccount = "ACC002";

        List<Transaction> transactions = transactionFilters.getTransactionsByReceiverAccount(receiverAccount);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getRecieverAccount().equals(receiverAccount)));
    }

    @Test
    public void testGetTransactionsByCurrency() throws SQLException, ClassNotFoundException {
        String currency = "USD";

        List<Transaction> transactions = transactionFilters.getTransactionsByCurrency(currency);

        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);  // Ensure at least one transaction meets the criteria
        assertTrue(transactions.stream().allMatch(t -> t.getCurrency().equals(currency)));
    }

    @Test
    public void testGetAllTransactions() throws SQLException, ClassNotFoundException {
        List<Transaction> transactions = transactionFilters.getAllTransactions();

        assertNotNull(transactions);
        assertEquals(50, transactions.size());  // Assuming there are 50 transactions in total
    }
}
