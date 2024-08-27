package com.nwb.cust.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDatabase {

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        String insertSQL = "INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        	//used prepared statement to avoid SQL Injection
        
        String[][] entries = {
            {"TXN001", "ACC001", "ACC002", "CREDIT", "500.00", "2024-08-01 10:00:00", "SUCCESS", "Payment for services", "CREDIT_CARD", "INR"},
            {"TXN002", "ACC003", "ACC004", "DEBIT", "750.50", "2024-08-02 11:15:00", "SUCCESS", "Office Supplies", "DEBIT_CARD", "USD"},
            // Add more rows as needed
        };

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Insert each row into the database
            for (String[] entry : entries) {
                pstmt.setString(1, entry[0]); // transactionId
                pstmt.setString(2, entry[1]); // senderAccountId
                pstmt.setString(3, entry[2]); // receiverAccountId
                pstmt.setString(4, entry[3]); // transactionType
                pstmt.setDouble(5, Double.parseDouble(entry[4])); // amount
                pstmt.setTimestamp(6, java.sql.Timestamp.valueOf(entry[5])); // transactionTime
                pstmt.setString(7, entry[6]); // transactionStatus
                pstmt.setString(8, entry[7]); // description
                pstmt.setString(9, entry[8]); // transactionMode
                pstmt.setString(10, entry[9]); // currency
                pstmt.addBatch(); // Add to batch
            }

            pstmt.executeBatch(); // Execute the batch

            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
