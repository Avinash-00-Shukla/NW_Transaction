package com.nwb.cust.model;

public class Transaction {
   private Long id;
   private String status;
   
   private double amount;
   
   

public Transaction(Long id, String status, double amount) {
	
	this.id = id;
	this.status = status;
	this.amount = amount;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}
   
   
   
   
}
