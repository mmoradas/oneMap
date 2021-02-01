package com.oneMap.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oneMap.utilities.Utility;


public class SearchResultPageView {

	WebDriver driver;
	
	public SearchResultPageView(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//img[contains(@src,'icons_Locator.png')]") WebElement imgLocationPoint;
	@FindBy(xpath="//p[@class='block wrap']") WebElement pLocationPointAddress;
	@FindBy(id="postal_code") WebElement textResultPostalCode;
	@FindBy(id="jpbtn") WebElement btnJourneyPlan;
	
	@FindBy(id="search-text-dest") WebElement inputDestination1;

	By divCarParks = By.id("divCarParks");
	By divDbsAtm = By.id("divDbsAtm");
	By divUobAtm = By.id("divUobAtm");
	By divOcbcAtm = By.id("divOcbcAtm");
	By divHawkercentres = By.id("divHawkercentres");
	By divChas = By.id("divChas");
	
	By btnTransport = By.xpath("//input[@class='transport lastRowButton']");
	By btnAmenities = By.xpath("//input[@class='amenities lastRowButton']");
	By btnMore = By.xpath("//input[@class='more lastRowButton']");
	
	
	public void clickLocationResult() {
		imgLocationPoint.click();
	}
	
	public String getResultAddress() {
		
		return textResultPostalCode.getText();
	}
	
	public String getLocationPointAddress() throws InterruptedException {
		
		Thread.sleep(2000);
		return pLocationPointAddress.getText();
	}
	
	public void clickJourneyButton() throws InterruptedException {
		
		if(btnJourneyPlan.isEnabled()) {
			btnJourneyPlan.click();
		}
		else {
			Thread.sleep(2000);
			btnJourneyPlan.click();
		}
		
	}
	
	public boolean verifyPresenceOfKeyElements() {
		
		if(!Utility.verifyElementIsPresent(driver, divCarParks)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, divDbsAtm)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, divUobAtm)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, divOcbcAtm)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, divOcbcAtm)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, divChas)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, btnTransport)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, btnAmenities)) {
			return false;
		}
		if(!Utility.verifyElementIsPresent(driver, btnMore)) {
			return false;
		}
		
		return true;
	}
}
