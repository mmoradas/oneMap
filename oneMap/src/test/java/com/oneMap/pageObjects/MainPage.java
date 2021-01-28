package com.oneMap.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	
	WebDriver driver;
	
	public MainPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//a[@id='my-text']") WebElement linkCloseGuide;
	@FindBy(xpath="//a[@class='wb collapsed']") WebElement linkMenu;
	@FindBy(id="search-text") WebElement inputSearch;
	@FindBy(id="toggleLocateMe") WebElement btnLocatePosition;
	
	
	
	public void closeGuide() {
		
		linkCloseGuide.click();
	}

	public void searchKeyword(String keyword) {
		
		inputSearch.sendKeys(keyword);
		inputSearch.sendKeys(Keys.ENTER);
	}
	
	public void viewMenu() {
		
		linkMenu.click();
	}
	
	public void locateCurrentPosition() {
		
		btnLocatePosition.click();
	}
}
