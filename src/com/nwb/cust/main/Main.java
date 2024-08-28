package com.nwb.cust.main;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionType;
import com.nwb.cust.repo.TransactionFiltersDAO;
import com.nwb.cust.repo.TransactionFiltersImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            TransactionFiltersDAO dao = new TransactionFiltersImpl();

            // 1. Retrieve Transactions by Date Range
            System.out.println("Transactions by Date Range:");

            List<Transaction> transactionsByDateRange = dao.getTransactionsByDateRange(
                    LocalDateTime.of(2024, 8, 1, 0, 0),
                    LocalDateTime.of(2024, 8, 31, 23, 59)
            );
            for (Transaction transaction : transactionsByDateRange) {
                System.out.println(transaction);
            }

            // 2. Retrieve Transactions by Exact Amount
            System.out.println("\nTransactions by Exact Amount:");
            List<Transaction> transactionsByAmount = dao.getTransactionsByAmount(500.00);
            for (Transaction transaction : transactionsByAmount) {
                System.out.println(transaction);
            }

            // 3. Retrieve Transactions by Transaction Type
            System.out.println("\nTransactions by Transaction Type:");
            List<Transaction> transactionsByType = dao.getTransactionsByType(TransactionType.CREDIT);
            for (Transaction transaction : transactionsByType) {
                System.out.println(transaction);
            }

            // 4. Retrieve Transactions by Description Keyword
            System.out.println("\nTransactions by Description Keyword:");
            List<Transaction> transactionsByDescription = dao.getTransactionsByDescription("Payment");
            for (Transaction transaction : transactionsByDescription) {
                System.out.println(transaction);
            }

            // 5. Retrieve a Transaction by Transaction ID
            System.out.println("\nTransaction by Transaction ID:");
            List<Transaction> transactionById = dao.getTransactionByTransactionId("TXN001");
            for (Transaction transaction : transactionById) {
                System.out.println(transaction);
            }

            // 6. Retrieve All Transactions
            System.out.println("\nAll Transactions:");
            List<Transaction> allTransactions = dao.getAllTransactions();
            for (Transaction transaction : allTransactions) {
                System.out.println(transaction);
            }

            // 7. Retrieve Transactions by Sender Account
            System.out.println("\nTransactions by Sender Account:");
            List<Transaction> transactionsBySenderAccount = dao.getTransactionsBySenderAccount("ACC001");
            for (Transaction transaction : transactionsBySenderAccount) {
                System.out.println(transaction);
            }

            // 8. Retrieve Transactions by Currency
            System.out.println("\nTransactions by Currency:");
            List<Transaction> transactionsByCurrency = dao.getTransactionsByCurrency("USD");
            for (Transaction transaction : transactionsByCurrency) {
                System.out.println(transaction);
            }

            // 9. Retrieve Transactions by Mode
            System.out.println("\nTransactions by Mode:");
            List<Transaction> transactionsByMode = dao.getTransactionsByMode(TransactionMode.CREDIT_CARD);
            for (Transaction transaction : transactionsByMode) {
                System.out.println(transaction);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
