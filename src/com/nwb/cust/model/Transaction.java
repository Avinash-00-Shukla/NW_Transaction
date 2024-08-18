package com.nwb.cust.model; 
import java.time.LocalDateTime;
 
public class Transaction { 

    private Long id; 
    private String type;
    private Double amount;
    private LocalDateTime date;
    private String description; 
    private TransactionStatus status;

    public Transaction() {
        // Default constructor
    }

    public Transaction(Long id, String type, Double amount, LocalDateTime date, String description, TransactionStatus status) {
        this.id = id;
		this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

	public void completeTransaction() {
        this.status = TransactionStatus.SUCCESS;
    }

    public void failTransaction() {
        this.status = TransactionStatus.FAILED;
    }

    public void cancelTransaction() {
        this.status = TransactionStatus.CANCELLED;
    }

    public boolean isPending() {
        return this.status == TransactionStatus.PENDING;
    }

    public boolean isCompleted() {
        return this.status == TransactionStatus.SUCCESS;
    }

    public boolean isFailed() {
        return this.status == TransactionStatus.FAILED;
    }
    
    public boolean isCancelled() {
        return this.status == TransactionStatus.CANCELLED;
    }
}
