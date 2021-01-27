package com.oneMap.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001 extends BaseTestClass {
	
	@Test
	void LoginTest() {
		
		if(driver.getTitle().equals("Guruasdfasdf")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
			LOGGER.info("Testcase Failed");
		}

	}
	
}
