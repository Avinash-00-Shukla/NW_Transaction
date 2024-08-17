package com.nwb.cust.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TransactionControllerTest.class, TransactionServiceTest.class })
public class AllTests {

}
