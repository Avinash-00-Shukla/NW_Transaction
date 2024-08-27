package com.nwb.cust.repo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nwb.cust.model.*;

public class TransactionStatusFilterImpl  implements TransactionStatusFilterDAO {
    
    // Retrieve transactions by status
    @Override
    public List<Transaction> getTransactionsByStatus(TransactionStatus status) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM Transactions WHERE transactionStatus = ?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, status.name());
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
            Timestamp transactionTime = resultSet.getTimestamp("timestamp");
            String description = resultSet.getString("description"); 
            String status = resultSet.getString("transactionStatus");
            String currency = resultSet.getString("currency");
            String senderAccount = resultSet.getString("senderAccountId");
            String recieverAccount = resultSet.getString("recieverAccountId");
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