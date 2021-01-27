package com.oneMap.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BusServiceTest extends BaseTestClass {
	

	@Test
	public void BusServiceTest_001() {
		
		if(driver.getTitle().equals("Guruasdfasdf")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
			LOGGER.info("Testcase Failed");
		}

	}
	
}
