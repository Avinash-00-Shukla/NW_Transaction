package com.nwb.cust.service;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.model.Transaction; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class TransactionHistory {
    private List<Transaction> transactions;

    // Constructor
    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    // Add a transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Retrieve transactions by user and date range
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactions.stream()
            .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
            .collect(Collectors.toList());
    }

    // Retrieve transactions by status
    public List<Transaction> getTransactionsByStatus(TransactionStatus status) {
        return transactions.stream()
            .filter(t -> t.getStatus().equals(status))
            .collect(Collectors.toList());
    }

    // Get a specific transaction by ID
    public Transaction getTransactionById(Long transactionId) {
        return transactions.stream()
            .filter(t -> t.getId().equals(transactionId))
            .findFirst()
            .orElse(null);
    }

    // Search transactions by amount
    public List<Transaction> searchTransactionsByAmount(double amount) {
        return transactions.stream()
            .filter(t -> t.getAmount() == amount)
            .collect(Collectors.toList());
    }

    // Search transactions by description (recipient/sender)
    public List<Transaction> searchTransactionsByDescription(String description) {
        return transactions.stream()
            .filter(t -> t.getDescription().contains(description))
            .collect(Collectors.toList());
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}
