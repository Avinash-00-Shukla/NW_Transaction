package com.nwb.cust.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionValidations;  

public class TransactionRepository {

    public List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() { 

        // Fake transactions for testing
        Random random = new Random();  
        for (int i = 0; i < 20; i++) {
            Transaction transaction = new Transaction(
                (long) (Math.random() * 1000000007),  
                getRandomTransactionModeString(random),  
                getRandomTransactionTypeString(random),  
                (Math.random() * 100000),  
                LocalDateTime.now().minusDays(random.nextInt(30)),  
                "Demo Transaction " + (i + 1),  
                getRandomTransactionStatusString(random),  
                "INR",
                "USER" + ((i + 1) % 5)
            );
            transactions.add(transaction);
        }
    }

    // Helper methods to generate random enum values as strings
    public void addTransaction(Transaction t){ 
        if(TransactionValidations.validateTransaction(t)) transactions.add(t);
    }

    private String getRandomTransactionModeString(Random random) {
        String[] modes = {"CREDIT_CARD", "DEBIT_CARD", "NEFT", "CASH", "UPI", "CHEQUE","TEST"};
        return modes[random.nextInt(modes.length)];
    }

    private String getRandomTransactionTypeString(Random random) {
        String[] types = {"CREDIT", "DEBIT","TEST"};
        return types[random.nextInt(types.length)];
    }

    private String getRandomTransactionStatusString(Random random) {
        String[] statuses = {"PENDING", "SUCCESS", "FAILED", "CANCELLED","TEST"};
        return statuses[random.nextInt(statuses.length)];
    }
}
