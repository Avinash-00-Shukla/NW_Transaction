package com.nwb.cust.repo;

import java.sql.SQLException;
import java.util.List;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionStatus;

public interface TransactionStatusFilterDAO {
    public List<Transaction> getTransactionsByStatus(TransactionStatus status) throws SQLException, ClassNotFoundException;
}