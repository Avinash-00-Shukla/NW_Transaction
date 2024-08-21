package com.nwb.cust.model;  


public class TransactionValidations {

    public static boolean validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        } 
        validateMode(transaction.getMode());
        validateType(transaction.getType());
        validateAmount(transaction.getAmount()); 
        validateStatus(transaction.getStatus()); 
        return true;
    } 

    private static void validateMode(TransactionMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Transaction mode must be specified");
        }
        try {
            TransactionMode.valueOf(mode.name());  
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction mode: " + mode);
        }
    }

    private static void validateType(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Transaction type must be specified");
        }
        try {
            TransactionType.valueOf(type.name());  
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction type: " + type);
        }
    }

    private static void validateAmount(Double amount) {
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Amount must be a non-negative value");
        }
    } 
    private static void validateStatus(TransactionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Transaction status must be specified");
        }
        try {
            TransactionStatus.valueOf(status.name());  
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction status: " + status);
        }
    }  
}
