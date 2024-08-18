package com.nwb.cust.repo; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; 
import com.nwb.cust.model.Transaction;
import com.nwb.cust.model.TransactionStatus;

public class TransactionRepository {

	public List<Transaction> transactions;

	public TransactionRepository() {
		//here data base will be conected but currently using only dummy data

		transactions = new ArrayList<>();
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "UPI", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For CANCELLED Testing", TransactionStatus.CANCELLED));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "NEFT", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For SUCCESS Testing", TransactionStatus.SUCCESS));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "UPI", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For FAILED Testing", TransactionStatus.FAILED));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "NEFT", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For PENDING Testing", TransactionStatus.PENDING));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "UPI", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For CANCELLED Testing", TransactionStatus.CANCELLED));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "CHEQUE", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For SUCCESS Testing", TransactionStatus.SUCCESS));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "CHEQUE", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For FAILED Testing", TransactionStatus.FAILED));
		transactions.add(new Transaction((long) (Math.random() * 1000000007), "CASH WITHDRAWN", (double) (Math.random() * 100000),
				LocalDateTime.now(), "For PENDING Testing", TransactionStatus.FAILED));

	} 
}
