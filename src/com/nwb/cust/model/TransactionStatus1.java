package com.nwb.cust.model;

public class TransactionStatus1 {
    private String transactionId;
    private Double amount;
    private String status;
    private String currency;

    public TransactionStatus() {
    }

    // Parameterized constructor
    public TransactionStatus(String transactionId, Double amount, String status, String currency) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.currency = currency;
    }

    // Constructor to initialize from Transaction object
    public TransactionStatus(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.status = transaction.getStatus();
        this.currency = transaction.getCurrency();
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "TransactionStatus{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
