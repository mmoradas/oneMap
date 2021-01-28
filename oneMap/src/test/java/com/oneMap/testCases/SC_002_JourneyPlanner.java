package com.oneMap.testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oneMap.pageObjects.JourneyPlannerPageView;
import com.oneMap.pageObjects.MainPage;
import com.oneMap.pageObjects.SearchResultPageView;

public class SC_002_JourneyPlanner extends BaseTestClass {

	@Test
	void verifyRouteToDestination1() throws InterruptedException {
		
		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		
		mainPage.closeGuide();
		
		mainPage.locateCurrentPosition();
		
		SearchResultPageView searchView = PageFactory.initElements(driver, SearchResultPageView.class);
		searchView.clickJourneyButton();
		
		JourneyPlannerPageView journey = PageFactory.initElements(driver, JourneyPlannerPageView.class);
		String destinationData = "BEAUTY WORLD MRT STATION EXIT B"; //Put this into excel data source
		journey.setDestination1Value(destinationData);
		
		if(!journey.verifyKeyElementPresent()) {
			Assert.assertTrue(false);
		}
		
		journey.clickDestinationResult();
		String resultDestination = journey.getDestinationResultValue();
		
		if(!resultDestination.contains(destinationData)) {
			LOGGER.info("Search criteria does not match with the result.");
			LOGGER.info("resultDestination: " + resultDestination);
			LOGGER.info("destinationData: " + destinationData);
			Assert.assertTrue(false);
		}

	}
	
	public void verifyRouteToDestination2() {
		
	}
}
