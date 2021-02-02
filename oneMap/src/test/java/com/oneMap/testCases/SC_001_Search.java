package com.oneMap.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.oneMap.pageObjects.MainPage;
import com.oneMap.pageObjects.SearchResultPageView;
import com.oneMap.utilities.ExcelConfig;


public class SC_001_Search extends BaseTestClass{

	ExcelConfig excel;
	
	public SC_001_Search() throws IOException {
		System.out.println("Constructor..");
		
		String sheetName = this.getClass().getSimpleName();
		excel = new ExcelConfig(sheetName);
		excel.readExcel();
	}
	
	@Test
	public void verifyKeywordSearch_TC_001(ITestContext test) throws InterruptedException, IOException {

		String testName="verifyKeywordSearch_TC_001";
		String keyword = excel.getData(testName, "SearchKeyWord");
		
		MainPage mainPage = PageFactory.initElements((WebDriver) driver, MainPage.class);
		mainPage.closeGuide();
		mainPage.searchKeyword(keyword);
		
		SearchResultPageView searchView = PageFactory.initElements((WebDriver) driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void verifyPostalCodeSearch_TC_002() throws InterruptedException {

		String testName="verifyPostalCodeSearch_TC_002";
		String PostalCode = excel.getData(testName, "PostalCode");
		
		MainPage mainPage = PageFactory.initElements((WebDriver) driver, MainPage.class);
		mainPage.closeGuide();
		
		mainPage.searchKeyword(PostalCode);
		
		SearchResultPageView searchView = PageFactory.initElements((WebDriver) driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false);
		}
		
	}
	
//	@Test
//	public void performMyLocationSearch_TC_003() throws InterruptedException {
//
//		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
//		
//		mainPage.closeGuide();
//		
//		mainPage.locateCurrentPosition();
//		
//		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
//		Thread.sleep(2000);
//		String resultPostalCode = searchView.getResultAddress();
//		String mapPostalCode = searchView.getLocationPointAddress();
//		
//		if(!mapPostalCode.contains(resultPostalCode)) {
//			Assert.assertTrue(false);
//		}
//		
//	}
	
}
