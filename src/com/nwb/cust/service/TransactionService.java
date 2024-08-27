package com.nwb.cust.service;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.model.TransactionType;
import com.nwb.cust.repo.TransactionFiltersImpl;
import com.nwb.cust.repo.TransactionStatusFilterImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {

    private TransactionFiltersImpl transactionFilters;
    private TransactionStatusFilterImpl transactionStatusFilter;

    // Constructor
    public TransactionService() { 
        transactionFilters = new TransactionFiltersImpl();
        transactionStatusFilter = new TransactionStatusFilterImpl();
    }
    
    // public boolean addTransaction (Long transactionId, String mode, String type, Double amount, LocalDateTime date,
    // String description, String status, String currency, String currentUser, String otherParty) {
    //     // Add transaction to the repository 
    //     try{
    //         Transaction t = new Transaction(transactionId, mode, type, amount, date, description, status, currency, currentUser, otherParty);
    //         repository.transactions.add(t);
    //     }
    //     catch(IllegalArgumentException e){  
    //         return false;
    //     }
    //     return true;
    // }

    // Filter transactions by a specific date range
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByDateRange(startDate, endDate);
    }

    // Filter transactions by exact amount
    public List<Transaction> getTransactionsByExactAmount(double amount) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByAmount(amount);
    }

    // Filter transactions by minimum amount
    public List<Transaction> getTransactionsByMinAmount(double minAmount) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByMinAmount(minAmount);
    }

    // Filter transactions by maximum amount
    public List<Transaction> getTransactionsByMaxAmount(double maxAmount) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByMaxAmount(maxAmount);
    }

    // Filter transactions by transaction type
    public List<Transaction> getTransactionsByType(TransactionType type) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByType(type);
    }

    // Filter transactions by transaction status
    public List<Transaction> getTransactionsByStatus(TransactionStatus status) throws SQLException, ClassNotFoundException{
        return transactionStatusFilter.getTransactionsByStatus(status);
    }

    // Filter transactions by transaction mode
    public List<Transaction> getTransactionsByMode(TransactionMode mode) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByMode(mode);
    }

    // Filter transactions by description (contains keywords)
    public List<Transaction> getTransactionsByDescription(String descriptionKeyword) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByDescription(descriptionKeyword);
    }

    // Retrieve a specific transaction by its ID
    public List<Transaction> getTransactionByTransactionId(String transactionId) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionByTransactionId(transactionId);
    }

    // Filter transactions by sender account number
    public List<Transaction> getTransactionsBySenderAccount(String senderAccount) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsBySenderAccount(senderAccount);
    }

    // Filter transactions by receiver account number
    public List<Transaction> getTransactionsByReceiverAccount(String receiverAccount) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByReceiverAccount(receiverAccount);
    } 

    // Filter transactions by currency type
    public List<Transaction> getTransactionsByCurrency(String currency) throws SQLException, ClassNotFoundException{
        return transactionFilters.getTransactionsByCurrency(currency);
    }

    // public List<Transaction> getAllTransactions() {
    //     return repository.transactions;
    // }
}
