package com.nwb.cust.test; 
import org.junit.BeforeClass; 
import com.nwb.cust.service.TransactionService;

public class TransactionRepositoryTest {
    private TransactionService service;

    @BeforeClass
    public void setUp() {
        service = new TransactionService();
    }
}
