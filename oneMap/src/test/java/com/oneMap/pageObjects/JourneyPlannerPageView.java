package com.oneMap.pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;

import com.oneMap.utilities.Utility;


public class JourneyPlannerPageView {

	WebDriver driver;
	public Logger LOGGER;
	public static final String DESTINATION1 = "Destination1";
	public static final String DESTINATION2 = "Destination2";
	
	public JourneyPlannerPageView(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//img[contains(@src,'icons_Locator.png')]") WebElement imgLocationPoint;
	@FindBy(id="postal_code") WebElement textResultPostalCode;
	@FindBy(id="jpbtn") WebElement btnJourneyPlan;
	@FindBy(id="addButton") WebElement btnAddDestination;
	
	By btnTransit = By.xpath("//label[@for='journey-transit']");
	By btnBus= By.xpath("//label[@for='journey-bus']");
	By btnDrive = By.xpath("//label[@for='journey-drive']");
	By btnWalk = By.xpath("//label[@for='journey-walk']");
	By btnBicycle = By.xpath("//label[@for='journey-bicycle']");
	By btnZoomOutInactive = By.xpath("//a[@class='leaflet-control-zoom-out leaflet-disabled']");
	
	@FindBy(id="search-text-dest") WebElement inputDestination1;
	@FindBy(id="search-text-dest2") WebElement inputDestination2;
	@FindBy(xpath="//div[@class='count']/img[contains(@src,'icon_multiroute_2.png')]") WebElement imgFirstDestinationResult;
	@FindBy(xpath="//a[@title='Zoom out']") WebElement btnZoomOut;
	
	By imgFinalDestinationResult = By.xpath("//img[contains(@src,'icon_DestinationLoc_512.png')]");
	By divFinalDestination = By.xpath("//div[@class='destination']");
	By divSwitchDest = By.xpath("//div[@class='search-switch']");
	By aCloseBtn = By.xpath("//a[@class='leaflet-popup-close-button']");
	By pLocationPointAddress = By.xpath("//p[@class='block wrap']");
	
	
	public void setDestinationValue(String destinationField, String value) throws InterruptedException {
		
		if(destinationField.equalsIgnoreCase(DESTINATION1)) {
			inputDestination1.sendKeys(value);
			Thread.sleep(2000);
			inputDestination1.sendKeys(Keys.ENTER);
		}
		else if(destinationField.equalsIgnoreCase(DESTINATION2)) {
			inputDestination2.sendKeys(value);
			Thread.sleep(2000);
			inputDestination2.sendKeys(Keys.ENTER);
		}
		else {
			System.out.print("Option selected is not supported for now.");
		}
		
		
	}
	
	public void setJourneyByCar() throws InterruptedException {

		if(driver.findElement(btnDrive).isEnabled()) {
			driver.findElement(btnDrive).click();
		}
		else {
			Thread.sleep(2000);
			driver.findElement(btnDrive).click();
		}
		
	}
	
	public void addJourneyDestination(String dest2Value) throws InterruptedException {
		
		if(btnAddDestination.isEnabled()) {
			btnAddDestination.click();
		}
		else {
			Thread.sleep(2000);
			btnAddDestination.click();
		}
		
	}
	
	public void zoomOutToMax() throws InterruptedException {
		
		boolean isDisabled = Utility.verifyElementIsPresent(driver, btnZoomOutInactive);
		while(!isDisabled){
			btnZoomOut.click();
			isDisabled = Utility.verifyElementIsPresent(driver, btnZoomOutInactive);
		}
	}
	
	public String getFinalDestinationResult() throws InterruptedException {
		
//		boolean isPresent = Utility.isElementFound(driver, imgFinalDestinationResult, 6);
//		int retries = 0;
//		while(!isPresent && retries < 5) {
//			btnZoomOut.click();
//			isPresent = Utility.isElementFound(driver, imgFinalDestinationResult, 6);
//			retries++;
//		}
//		
//		if(!isPresent) {
//			assertTrue(false,"Final Destionation Image is Not Found.");
//		}

		
		
//		WebElement elem = new WebDriverWait(driver, 10).until(
//			    ExpectedConditions.presenceOfElementLocated(imgFinalDestinationResult)
//			);
//		elem.click();
		
//		isPresent = Utility.isElementFound(driver, divFinalDestination, 6);
//		if(!isPresent) {
//			Thread.sleep(2000);
//			isPresent = Utility.isElementFound(driver, divFinalDestination, 6);
//		}
		
//		boolean isPresent = Utility.isElementFound(driver, aCloseBtn, 6);
//		if (isPresent) { 
//			driver.findElement(aCloseBtn).click();
//		}
		Thread.sleep(4000);
		driver.findElement(imgFinalDestinationResult).click();
		return driver.findElement(divFinalDestination).getText();
		
	}
	
	public void clickSwitchDestination() {
		
		boolean isPresent = Utility.isElementFound(driver, imgFinalDestinationResult, 6);
		if(isPresent) {
			driver.findElement(divSwitchDest).click();
		}
		else{
			Assert.assertTrue(false,"Switch Destination button not found.");
		}
	}
	
	public boolean verifyKeyElementPresent() {
		return Utility.verifyElementIsPresent(driver, btnTransit);
	}
	
	public String getResultAddress() {
		String value = textResultPostalCode.getText();
		LOGGER.info("textResultPostalCode Text Value:" + value);
		return value;
	}
	
	public String getLocationPointAddress() {
		String value = textResultPostalCode.getText();
		LOGGER.info("LocationPointAddress Text Value:" + value);
		return value;
	}
	
	public String getOriginAddress() {
		String value = driver.findElement(pLocationPointAddress).getText();
		LOGGER.info("LocationPointAddress Text Value:" + value);
		return value;
	}
}
