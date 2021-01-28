package com.oneMap.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oneMap.utilities.Utility;


public class JourneyPlannerPageView {

	WebDriver driver;
	
	public JourneyPlannerPageView(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//img[contains(@src,'icons_Locator.png')]") WebElement imgLocationPoint;
	@FindBy(xpath="//p[@class='block wrap']") WebElement pLocationPointAddress;
	@FindBy(id="postal_code") WebElement textResultPostalCode;
	@FindBy(id="jpbtn") WebElement btnJourneyPlan;
	
	By btnTransit = By.xpath("//label[@for='journey-transit']");
	By btnBus= By.xpath("//label[@for='journey-bus']");
	By btnDrive = By.xpath("//label[@for='journey-drive']");
	By btnWalk = By.xpath("//label[@for='journey-walk']");
	By btnBicycle = By.xpath("//label[@for='journey-bicycle']");
	
	@FindBy(id="search-text-dest") WebElement inputDestination1;
	@FindBy(xpath="//div[@class='destination']") WebElement divDestinationResult;
	@FindBy(xpath="//img[contains(@src,'icon_DestinationLoc_512.png')]") WebElement imgDestinationResult; 
	@FindBy(xpath="//a[@title='Zoom out']") WebElement btnZoomOut;
	
	
	
	
	public void setDestination1Value(String value) throws InterruptedException {
		
		inputDestination1.sendKeys(value);
		Thread.sleep(2000);
		inputDestination1.sendKeys(Keys.ENTER);
	}
	
	public void clickDestinationResult() throws InterruptedException {
		
		//work around for maybe i consider a bug. By right, both destination and source be present on same screen view.
		Thread.sleep(1000);
		btnZoomOut.click();
		Thread.sleep(1000);
		btnZoomOut.click();
		Thread.sleep(1000);
		btnZoomOut.click();
		Thread.sleep(1000);
		btnZoomOut.click();
		
		Thread.sleep(2000);
		imgDestinationResult.click();
	}
	
	public String getDestinationResultValue() {
		
		String resultValue = divDestinationResult.getText();

		return resultValue;
	}
	
	public boolean verifyKeyElementPresent() {
		
		return Utility.verifyElementIsPresent(driver, btnTransit);
	}
	
	public String getResultAddress() {
		
		return textResultPostalCode.getText();
	}
	
	public String getLocationPointAddress() {
		
		return pLocationPointAddress.getText();
	}
}
