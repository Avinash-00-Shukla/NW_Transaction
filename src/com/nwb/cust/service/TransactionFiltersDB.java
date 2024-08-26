package com.nwb.cust.service;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionStatus;
import com.nwb.cust.model.TransactionType;
import com.nwb.cust.repo.DBConnection;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionFiltersDB {

    // Retrieve transactions by date range
    public static List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM Transactions WHERE timestamp BETWEEN ? and ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setTimestamp(1, Timestamp.valueOf(startDate));
        pstmt.setTimestamp(2, Timestamp.valueOf(endDate));
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by exact amount
    public static List<Transaction> getTransactionsByAmount(double amount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, amount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by minimum amount
    public static List<Transaction> getTransactionsByMinAmount(double minAmount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount >= ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, minAmount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by maximum amount
    public static List<Transaction> getTransactionsByMaxAmount(double maxAmount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount <= ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, maxAmount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by type
    public static List<Transaction> getTransactionsByType(TransactionType type) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionType = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, type.name());
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by status
    public static List<Transaction> getTransactionsByStatus(TransactionStatus status) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionStatus = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, status.name());
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by mode
    public static List<Transaction> getTransactionsByMode(TransactionMode mode) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionMode = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, mode.name());
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by description containing keywords
    public static List<Transaction> getTransactionsByDescription(String descriptionKeyword) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE description LIKE ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, "%"+descriptionKeyword+"%");
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve a specific transaction by ID
    public static List<Transaction> getTransactionByTransactionId(String transactionId) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionId = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, transactionId);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by sender account number
    public static List<Transaction> getTransactionsBySenderAccount(String senderAccount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE senderAccount = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, senderAccount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by receiver account number
    public static List<Transaction> getTransactionsByReceiverAccount(String receiverAccount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE receiverAccountId = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, receiverAccount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }  

    // Retrieve transactions by currency type
    public static List<Transaction> getTransactionsByCurrency(String currency) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE currency = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currency);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Converts ResultSet to Transaction Objects and adds them to a list
    public static List<Transaction> resultSetToTransactionList(ResultSet resultSet) throws SQLException, ClassNotFoundException{
        List<Transaction> filteredList = new ArrayList<Transaction>();
        while (resultSet.next()){
            String id = resultSet.getString("transactionId");   // rename id to transactionId after deleting below line
            Long transactionId = Long.parseLong(id);   //this will be removed after using String transactionId
            String mode = resultSet.getString("transactionMode");
            String type = resultSet.getString("transactionType");
            Double amount = resultSet.getDouble("amount");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            String description = resultSet.getString("description"); 
            String status = resultSet.getString("transactionStatus");
            String currency = resultSet.getString("currency");
            String senderAccount = resultSet.getString("senderAccountId");
            String recieverAccount = resultSet.getString("recieverAccountId");
            LocalDateTime date = null;
            if (timestamp != null){
                date = timestamp.toLocalDateTime();
            }
            Transaction transaction = new Transaction(transactionId, mode, type, amount, date, description, status, currency, senderAccount, recieverAccount);
            filteredList.add(transaction);
        }
        return filteredList;
    }

}