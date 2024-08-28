package com.nwb.cust.test;

import static org.junit.Assert.*;
import com.nwb.cust.model.Transaction; 
import com.nwb.cust.model.TransactionStatus; 
import com.nwb.cust.repo.TransactionStatusFilterImpl; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransactionStatusImplTest {
    private static Connection connection;
    private static TransactionStatusFilterImpl transactionFilters;

    @Before
    public void setup() throws ClassNotFoundException, SQLException {
        // Initialize the database connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "system";
        String PASS = "sys123";
        connection = DriverManager.getConnection(DB_URL, USER, PASS); 
        transactionFilters = new TransactionStatusFilterImpl();
    }

    @After
    public void tearDown() throws SQLException {
        // Close the connection after all tests are done
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testGetTransactionsByStatusSUCCESS() throws SQLException, ClassNotFoundException {
        TransactionStatus status = TransactionStatus.SUCCESS;
        List<Transaction> transactions = transactionFilters.getTransactionsByStatus(status);
        assertNotNull(transactions);
        assertTrue(!transactions.isEmpty());  
        assertTrue(transactions.stream().allMatch(t -> t.getStatus() == status));
    }

    @Test
    public void testGetTransactionsByStatusFAILED() throws SQLException, ClassNotFoundException {
        TransactionStatus status = TransactionStatus.FAILED;
        List<Transaction> transactions = transactionFilters.getTransactionsByStatus(status);
        assertNotNull(transactions);
        assertTrue(!transactions.isEmpty());  
        assertTrue(transactions.stream().allMatch(t -> t.getStatus() == status));
    }

    @Test
    public void testGetTransactionsByStatusCANCELLED() throws SQLException, ClassNotFoundException {
        TransactionStatus status = TransactionStatus.CANCELLED;
        List<Transaction> transactions = transactionFilters.getTransactionsByStatus(status);
        assertNotNull(transactions);
        assertTrue(!transactions.isEmpty());  
        assertTrue(transactions.stream().allMatch(t -> t.getStatus() == status));
    }

    @Test
    public void testGetTransactionsByStatusPENDING() throws SQLException, ClassNotFoundException {
        TransactionStatus status = TransactionStatus.PENDING;
        List<Transaction> transactions = transactionFilters.getTransactionsByStatus(status);
        assertNotNull(transactions);
        assertTrue(!transactions.isEmpty());  
        assertTrue(transactions.stream().allMatch(t -> t.getStatus() == status));
    }
}
