package com.nwb.cust.model; 
import java.time.LocalDateTime; 


public class TransactionValidations {

    public void validateTransaction(Transaction transaction) {
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
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Transaction ID must be a positive number");
        }
    }

    private void validateMode(TransactionMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Transaction mode must be specified");
        } 
    }

    private void validateType(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Transaction type must be specified");
        }
    }

    private void validateAmount(Double amount) {
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Amount must be a non-negative value");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Transaction date must be specified");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction description cannot be empty");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Transaction description cannot exceed 255 characters");
        }
    }

    private void validateStatus(TransactionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Transaction status must be specified");
        }
    }

    private void validateCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency must be specified");
        }
        if (!isValidCurrencyCode(currency)) {
            throw new IllegalArgumentException("Invalid currency code: " + currency);
        }
    }

    private boolean isValidCurrencyCode(String currency) { 
        return currency.matches("^[A-Z]{3}$");   
    }
}
