package com.nwb.cust.test; 
import org.junit.*;

// import com.nwb.cust.repo.TransactionRepository;
import com.nwb.cust.service.TransactionService;

public class TransactionControllerTest { 
	    private TransactionService service;  
		@BeforeClass
        public void setUp() {
            service = new TransactionService();
        }
}
