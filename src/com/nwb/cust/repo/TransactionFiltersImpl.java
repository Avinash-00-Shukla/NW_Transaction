package com.nwb.cust.repo;

import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionMode;
import com.nwb.cust.model.TransactionType;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionFiltersImpl implements TransactionFiltersDAO{

    // Retrieve transactions by date range
    @Override
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM Transactions WHERE transactionTime BETWEEN ? and ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setTimestamp(1, Timestamp.valueOf(startDate));
        pstmt.setTimestamp(2, Timestamp.valueOf(endDate));
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by exact amount
    @Override
    public List<Transaction> getTransactionsByAmount(double amount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, amount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by minimum amount
    @Override
    public List<Transaction> getTransactionsByMinAmount(double minAmount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount >= ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, minAmount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by maximum amount
    @Override
    public List<Transaction> getTransactionsByMaxAmount(double maxAmount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE amount <= ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, maxAmount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by type
    @Override
    public List<Transaction> getTransactionsByType(TransactionType type) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionType = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, type.name());
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by mode
    @Override
    public List<Transaction> getTransactionsByMode(TransactionMode mode) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionMode = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, mode.name());
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by description containing keywords
    @Override
    public List<Transaction> getTransactionsByDescription(String descriptionKeyword) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE description LIKE ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, "%"+descriptionKeyword+"%");
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve a specific transaction by ID
    @Override
    public List<Transaction> getTransactionByTransactionId(String transactionId) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionId = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, transactionId);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by sender account number
    @Override
    public List<Transaction> getTransactionsBySenderAccount(String senderAccount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE senderAccountId = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, senderAccount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Retrieve transactions by receiver account number
    @Override
    public List<Transaction> getTransactionsByReceiverAccount(String receiverAccount) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE receiverAccountId = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, receiverAccount);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }  

    // Retrieve transactions by currency type
    @Override
    public List<Transaction> getTransactionsByCurrency(String currency) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE currency = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currency);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    @Override
    public List<Transaction> getAllTransactions() throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();
        return resultSetToTransactionList(resultSet);
    }

    // Converts ResultSet to Transaction Objects and adds them to a list
    public static List<Transaction> resultSetToTransactionList(ResultSet resultSet) throws SQLException, ClassNotFoundException{
        List<Transaction> filteredList = new ArrayList<Transaction>();
        while (resultSet.next()){
            String transactionId = resultSet.getString("transactionId");
            String mode = resultSet.getString("transactionMode");
            String type = resultSet.getString("transactionType");
            Double amount = resultSet.getDouble("amount");
            Timestamp transactionTime = resultSet.getTimestamp("transactionTime");
            String description = resultSet.getString("description"); 
            String status = resultSet.getString("transactionStatus");
            String currency = resultSet.getString("currency");
            String senderAccount = resultSet.getString("senderAccountId");
            String recieverAccount = resultSet.getString("receiverAccountId");
            LocalDateTime date = null;
            if (transactionTime != null){
                date = transactionTime.toLocalDateTime();
            }
            Transaction transaction = new Transaction(transactionId, mode, type, amount, date, description, status, currency, senderAccount, recieverAccount);
            filteredList.add(transaction);
        }
        return filteredList;
    }

}