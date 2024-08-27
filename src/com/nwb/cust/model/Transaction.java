package com.nwb.cust.model; 
import java.time.LocalDateTime;
 
public class Transaction {  
    private String transactionId; 
    private TransactionMode mode;
    private TransactionType type;
    private Double amount;
    private LocalDateTime date;
    private String description; 
    private TransactionStatus status;
    private String currency;
    private String senderAccount;
    private String recieverAccount;


    public Transaction() {
        // Default constructor
    }
    
    public Transaction(String transactionId, String mode, String type, Double amount, LocalDateTime date,
                       String description, String status, String currency, String senderAccount, String recieverAccount) throws IllegalArgumentException{ 
        this.transactionId = transactionId;
        this.setMode(mode); 
        this.setType(type);
        this.setStatus(status);
        this.amount = amount;
        this.date = date != null ? date : LocalDateTime.now();  
        this.description = description; 
        this.currency = currency;  
        this.senderAccount = senderAccount;
        this.recieverAccount = recieverAccount;
        // if(this.type == TransactionType.CREDIT){
        //     this.senderAccount = otherParty; 
        //     this.recieverAccount = currentUser;  
        // }
        // if(this.type == TransactionType.DEBIT){
        //     this.senderAccount = otherParty; 
        //     this.recieverAccount = currentUser;  
        // }

        // Validation for transaction 
        TransactionValidations.validateTransaction(this);
    } 

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }
 
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    } 

    public TransactionMode getMode() {
        return mode;
    } 

    public void setMode (String mode) throws IllegalArgumentException{
        try {
            this.mode =  TransactionMode.valueOf(mode.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TransactionMode: " + mode);
        }  
    }
 
    public TransactionType getType() {
        return type;
    }

    public void setType(String type) {
        try {
            this.type = TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TransactionType: " + type);
        }
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

    public TransactionStatus getStatus(){
        return status; 
    }

    public void setStatus(String status) throws IllegalArgumentException{
        try {
            this.status = TransactionStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TransactionStatus: " + status);
        }
    } 

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", mode='" + mode + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

	public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String sender) {
        this.senderAccount = sender;
    }

    public String getRecieverAccount() {
        return recieverAccount;
    }

    public void setRecieverAccount(String reciever) {
        this.recieverAccount = reciever;
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
