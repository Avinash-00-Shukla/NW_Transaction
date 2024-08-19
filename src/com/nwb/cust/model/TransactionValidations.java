package com.nwb.cust.model; 
import java.time.LocalDateTime; 


public class TransactionValidations {

    public static boolean validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        validateId(transaction.getId());
        validateMode(transaction.getMode());
        validateType(transaction.getType());
        validateAmount(transaction.getAmount());
        validateDate(transaction.getDate());
        validateDescription(transaction.getDescription());
        validateStatus(transaction.getStatus());
        validateCurrency(transaction.getCurrency());
        return true;
    }

    private static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Transaction ID must be a positive number");
        }
    }

    private static void validateMode(TransactionMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Transaction mode must be specified");
        } 
    }

    private static void validateType(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Transaction type must be specified");
        }
    }

    private static void validateAmount(Double amount) {
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Amount must be a non-negative value");
        }
    }

    private static void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Transaction date must be specified");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction description cannot be empty");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Transaction description cannot exceed 255 characters");
        }
    }

    private static void validateStatus(TransactionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Transaction status must be specified");
        }
    }

    private static void validateCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency must be specified");
        }
        if (!isValidCurrencyCode(currency)) {
            throw new IllegalArgumentException("Invalid currency code: " + currency);
        }
    }

    private static boolean isValidCurrencyCode(String currency) { 
        return currency.matches("^[A-Z]{3}$");   
    }
}
