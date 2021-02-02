package com.oneMap.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oneMap.pageObjects.JourneyPlannerPageView;
import com.oneMap.pageObjects.MainPage;
import com.oneMap.pageObjects.SearchResultPageView;
import com.oneMap.utilities.ExcelConfig;

public class SC_002_JourneyPlanner extends BaseTestClass {

	ExcelConfig excelData;
	
	public SC_002_JourneyPlanner() throws IOException {
		
		String sheetName = this.getClass().getSimpleName();
		excelData = new ExcelConfig(sheetName);
		excelData.readExcel();
	}

	@Test
	void verifyRouteToDestination1_TC_003() throws InterruptedException {
		
		String testName="verifyRouteToDestination1_TC_003";
		String source = excelData.getData(testName, "SourceLocation");
		String destinationData = excelData.getData(testName, "Destination1");
		
		MainPage mainPage = PageFactory.initElements((WebDriver) driver, MainPage.class);
		mainPage.closeGuide();
		mainPage.searchKeyword(source);
		
		SearchResultPageView searchView = PageFactory.initElements((WebDriver) driver, SearchResultPageView.class);
		searchView.clickJourneyButton();
		
		JourneyPlannerPageView journey = PageFactory.initElements((WebDriver) driver, JourneyPlannerPageView.class);
		journey.setDestinationValue(JourneyPlannerPageView.DESTINATION1, destinationData);
		//journey.zoomOutToMax();
		
		if(!journey.verifyKeyElementPresent()) {
			Assert.assertTrue(false);
		}
		
		String resultDestination = journey.getFinalDestinationResult();
		
		if(!resultDestination.contains(destinationData)) {
			LOGGER.info("Search criteria does not match with the result.");
			LOGGER.info("resultDestination: " + resultDestination);
			LOGGER.info("destinationData: " + destinationData);
			Assert.assertTrue(false, "Search keyword '" + destinationData + "' is not found in search results."  );
		}

	}
	
	@Test
	public void verifyRouteToDestination2_TC_004() throws InterruptedException {
		
		String testName="verifyRouteToDestination2_TC_004";
		String source = excelData.getData(testName, "SourceLocation");
		String destination1Data = excelData.getData(testName, "Destination1");
		String destination2Data = excelData.getData(testName, "Destination2");
		
		MainPage mainPage = PageFactory.initElements((WebDriver) driver, MainPage.class);
		mainPage.closeGuide();
		
		mainPage.searchKeyword(source);
		
		SearchResultPageView searchView = PageFactory.initElements((WebDriver) driver, SearchResultPageView.class);
		String resultPostalCode = searchView.getResultAddress();
		String mapPostalCode = searchView.getLocationPointAddress();
		
		if(!mapPostalCode.contains(resultPostalCode)) {
			Assert.assertTrue(false, "Search keyword /'" + source + "/' is not found in results."  );
		}
		
		JourneyPlannerPageView journey = PageFactory.initElements((WebDriver) driver, JourneyPlannerPageView.class);
		searchView.clickJourneyButton();
		journey.setDestinationValue(JourneyPlannerPageView.DESTINATION1, destination1Data);
		
		if(!journey.verifyKeyElementPresent()) {
			Assert.assertTrue(false,"Key Element not present during verification.");
		}
		
		journey.setJourneyByCar();
		journey.addJourneyDestination(destination2Data);
		journey.setDestinationValue(JourneyPlannerPageView.DESTINATION2, destination2Data);
		
		String resultFinalDestination = journey.getFinalDestinationResult();
		
		if(!resultFinalDestination.contains(destination2Data)) {
			LOGGER.info("Search criteria does not match with the result.");
			LOGGER.info("resultDestination: " + resultFinalDestination);
			LOGGER.info("destinationData: " + destination2Data);
			Assert.assertTrue(false, "Search keyword '" + destination2Data + "' is not found in search results."  );
		}
		
	}
	
	@Test
	void verifySwitchDestination_TC_005() throws InterruptedException {
		
		String testName="verifySwitchDestination_TC_005";
		String source = excelData.getData(testName, "SourceLocation");
		String destinationData = excelData.getData(testName, "Destination1");
		
		MainPage mainPage = PageFactory.initElements((WebDriver) driver, MainPage.class);
		mainPage.closeGuide();
		mainPage.searchKeyword(source);
		
		SearchResultPageView searchView = PageFactory.initElements((WebDriver) driver, SearchResultPageView.class);
		searchView.clickJourneyButton();
		String origin = searchView.getDestination1Value();
		
		JourneyPlannerPageView journey = PageFactory.initElements((WebDriver) driver, JourneyPlannerPageView.class);
		journey.setDestinationValue(JourneyPlannerPageView.DESTINATION1, destinationData);
		
		if(!journey.verifyKeyElementPresent()) {
			Assert.assertTrue(false);
		}
		
		String resultDestination = journey.getFinalDestinationResult();
		
		if(!resultDestination.contains(destinationData)) {
			LOGGER.info("Search criteria does not match with the result.");
			LOGGER.info("resultDestination: " + resultDestination);
			LOGGER.info("destinationData: " + destinationData);
			Assert.assertTrue(false, "Search keyword '" + destinationData + "' is not found in search results."  );
		}

		journey.clickSwitchDestination();
		String newDestination = journey.getFinalDestinationResult();
		LOGGER.info("newDestination: " + newDestination);
		LOGGER.info("origin: " + origin);
		if(!newDestination.contains(origin)) {
			Assert.assertTrue(false, "Looks like it is not switched."  );
		}
		
	}
	
}
