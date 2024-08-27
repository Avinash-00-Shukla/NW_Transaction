package com.nwb.cust.repo;
import com.nwb.cust.model.*;


public interface TransactionStatusDAO {
    void addTransaction(Transaction transaction) throws SQLException;
    Transaction getTransactionById(String transactionId) throws SQLException;
    // Other methods...
}