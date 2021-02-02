package com.oneMap.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.oneMap.utilities.Utility;

public class MainPage {
	
	WebDriver driver;
	
	public MainPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//a[@id='my-text']") WebElement linkCloseGuide;
	@FindBy(xpath="//a[@class='wb collapsed']") WebElement linkMenu;
	@FindBy(id="toggleLocateMe") WebElement btnLocatePosition;
	
	By inputSearch = By.xpath("//input[@id='search-text']");
	

	public void closeGuide() {
		
		linkCloseGuide.click();
	}

	public void searchKeyword(String keyword) throws InterruptedException {
		
//		Thread.sleep(2000);
//		this.driver.findElement(inputSearch).sendKeys(keyword);
//		Thread.sleep(2000);
//		this.driver.findElement(inputSearch).sendKeys(Keys.ENTER);
		
		boolean isFound = Utility.isElementFound(driver, inputSearch, 10);
		if(isFound) {
			driver.findElement(inputSearch).click();
			Thread.sleep(2000);
			driver.findElement(inputSearch).sendKeys(keyword);
			Thread.sleep(2000);
			driver.findElement(inputSearch).sendKeys(Keys.ENTER);
		}
		else {
			Assert.assertTrue(false,"Search Element is not found.");
		}

		
	}
	
	public void viewMenu() throws InterruptedException {
		
		Thread.sleep(2000);
		linkMenu.click();
	}
	
	public void locateCurrentPosition() throws InterruptedException {
		
		Thread.sleep(2000);
		btnLocatePosition.click();
	}
}
