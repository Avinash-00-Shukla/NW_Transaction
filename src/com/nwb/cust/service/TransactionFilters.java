package com.nwb.cust.service;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.model.TransactionType;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.stream.Collectors;

public class TransactionFilters {

    // Retrieve transactions by date range
    public static List<Transaction> getTransactionsByDateRange(List<Transaction> transactions, LocalDateTime startDate, LocalDateTime endDate) {
        return transactions.stream()
            .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
            .collect(Collectors.toList());
    }

    // Retrieve transactions by exact amount
    public static List<Transaction> getTransactionsByAmount(List<Transaction> transactions, double amount) {
        return transactions.stream()
            .filter(t -> t.getAmount() == amount)
            .collect(Collectors.toList());
    }

    // Retrieve transactions by minimum amount
    public static List<Transaction> getTransactionsByMinAmount(List<Transaction> transactions, double minAmount) {
        return transactions.stream()
            .filter(t -> t.getAmount() >= minAmount)
            .collect(Collectors.toList());
    }

    // Retrieve transactions by maximum amount
    public static List<Transaction> getTransactionsByMaxAmount(List<Transaction> transactions, double maxAmount) {
        return transactions.stream()
            .filter(t -> t.getAmount() <= maxAmount)
            .collect(Collectors.toList());
    }

    // Retrieve transactions by type
    public static List<Transaction> getTransactionsByType(List<Transaction> transactions, TransactionType type) {
        return transactions.stream()
            .filter(t -> t.getType().equals(type))
            .collect(Collectors.toList());
    }

    // Retrieve transactions by status
    public static List<Transaction> getTransactionsByStatus(List<Transaction> transactions, TransactionStatus status) {
        return transactions.stream()
            .filter(t -> t.getStatus().equals(status))
            .collect(Collectors.toList());
    }

    // Retrieve transactions by mode
    public static List<Transaction> getTransactionsByMode(List<Transaction> transactions, TransactionMode mode) {
        return transactions.stream()
            .filter(t -> t.getMode().equals(mode))
            .collect(Collectors.toList());
    }

    // Retrieve transactions by description containing keywords
    public static List<Transaction> getTransactionsByDescription(List<Transaction> transactions, String descriptionKeyword) {
        return transactions.stream()
            .filter(t -> t.getDescription().contains(descriptionKeyword))
            .collect(Collectors.toList());
    }

    // Retrieve a specific transaction by ID
    public static Transaction getTransactionByTransactionId(List<Transaction> transactions, Long transactionId) {
        return transactions.stream()
            .filter(t -> t.getTransactionId().equals(transactionId))
            .findFirst()
            .orElse(null);
    }

    // Retrieve transactions by sender account number
    public static List<Transaction> getTransactionsBySenderAccount(List<Transaction> transactions, String senderAccount) {
        return transactions.stream()
            .filter(t -> t.getSenderAccount().equals(senderAccount)) 
            .collect(Collectors.toList());
    }

    // Retrieve transactions by receiver account number
    public static List<Transaction> getTransactionsByReceiverAccount(List<Transaction> transactions, String receiverAccount) {
        return transactions.stream()
            .filter(t -> t.getRecieverAccount().equals(receiverAccount))
            .collect(Collectors.toList());
    }  

    // Retrieve transactions by currency type
    public static List<Transaction> getTransactionsByCurrency(List<Transaction> transactions, String currency) {
        return transactions.stream()
            .filter(t -> t.getCurrency().equals(currency))
            .collect(Collectors.toList());
    }
}
