package com.oneMap.testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oneMap.pageObjects.MainPage;
import com.oneMap.pageObjects.SearchResultPageView;

public class SC_001_Search extends BaseTestClass{

	
	@Test
	public void performKeywordSearch_TC_001() {

		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		
		mainPage.closeGuide();
		
		String keyword = "Marina Mall"; // Put this to excel data source
		mainPage.searchKeyword(keyword);
		
		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}

	}
	
	@Test
	public void performPostalCodeSearch_TC_002() {

		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		
		mainPage.closeGuide();
		
		String PostalCode = "339780"; // Put this to excel data source
		mainPage.searchKeyword(PostalCode);
		
		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}
		
	}
	
	
	@Test
	public void performMyLocationSearch_TC_003() throws InterruptedException {

		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		
		mainPage.closeGuide();
		
		mainPage.locateCurrentPosition();
		
		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
		Thread.sleep(2000);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}
		
	}
	
}
