package com.oneMap.testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oneMap.pageObjects.MainPage;
import com.oneMap.pageObjects.SearchResultPageView;

public class Test_Search_TC_001 extends BaseTestClass{

	
	@Test
	public void performSearch() {

		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		
		mainPage.closeGuide();
		
		mainPage.searchKeyword("Marina Mall");
		
		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}
		
		
	}
}
