package com.nwb.cust.service;
import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.repo.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {
    private TransactionHistory transactionHistory;
    private TransactionRepository repository;
    // Constructor
    public TransactionService() {
        transactionHistory = new TransactionHistory();
        repository = new TransactionRepository();
    }

    // View transaction history with optional filters
    public String viewTransactionHistory(LocalDateTime startDate, LocalDateTime endDate, TransactionStatus status) {
        List<Transaction> filteredTransactions = transactionHistory.getTransactionsByDateRange(startDate, endDate);
        
        if (status != null) {
            filteredTransactions = transactionHistory.getTransactionsByStatus(status);
        }

        if (filteredTransactions.isEmpty()) {
            return "No transaction history available for the selected criteria.";
        }

        // Convert the list of transactions to a readable format
        StringBuilder transactionHistoryString = new StringBuilder("Transaction History:\n");
        for (Transaction transaction : filteredTransactions) {
            transactionHistoryString.append(transaction.toString()).append("\n");
        }

        return transactionHistoryString.toString();
    }

    // Add a transaction
    public void addTransaction(Transaction transaction) {
        transactionHistory.addTransaction(transaction);
    }

    // Search transactions by amount
    public String searchTransactionsByAmount(double amount) {
        List<Transaction> transactions = transactionHistory.searchTransactionsByAmount(amount);

        if (transactions.isEmpty()) {
            return "No transactions found for the specified amount.";
        }

        // Convert the list of transactions to a readable format
        StringBuilder result = new StringBuilder("Transactions matching the amount:\n");
        for (Transaction transaction : transactions) {
            result.append(transaction.toString()).append("\n");
        }

        return result.toString();
    }

    // Search transactions by description (recipient/sender)
    public String searchTransactionsByDescription(String description) {
        List<Transaction> transactions = transactionHistory.searchTransactionsByDescription(description);

        if (transactions.isEmpty()) {
            return "No transactions found for the specified description.";
        }

        // Convert the list of transactions to a readable format
        StringBuilder result = new StringBuilder("Transactions matching the description:\n");
        for (Transaction transaction : transactions) {
            result.append(transaction.toString()).append("\n");
        }

        return result.toString();
    }
    
    // Get a specific transaction by ID
    public String getTransactionDetails(Long transactionId) {
        Transaction transaction = transactionHistory.getTransactionById(transactionId);
        if (transaction != null) {
            return "Transaction Details:\n" + transaction.toString();
        } else {
            return "Transaction not found.";
        }
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionHistory.getAllTransactions();
    }
}
