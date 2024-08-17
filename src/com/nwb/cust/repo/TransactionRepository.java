package com.nwb.cust.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nwb.cust.model.Transaction;

public class TransactionRepository {

		private List<Transaction> transactions;
		
		public TransactionRepository() {
			transactions= new ArrayList<>();
			
			transactions.add(new Transaction(1L,"SUCCESS", 100.0));
			transactions.add(new Transaction(2L, "PENDING", 200.0));
	        transactions.add(new Transaction(3L, "FAILED", 300.0));
	        transactions.add(new Transaction(4L, "SUCCESS", 400.0));
	        
	        
		}
		
		public List<Transaction> findAll() {
	        return transactions;
	    }

	    public List<Transaction> findByStatus(String status) {
	        return transactions.stream()
	                .filter(transaction -> transaction.getStatus().equalsIgnoreCase(status))
	                .collect(Collectors.toList());
	    }
		
		
}
