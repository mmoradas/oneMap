package com.oneMap.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	
	public static String captureScreen(WebDriver driver, String tcName) throws IOException {
		
		TakesScreenshot sc = (TakesScreenshot)driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		String fullPath = System.getProperty("user.dir") + "/Screenshots/" + tcName + ".png";
		File target = new File(fullPath);
		FileUtils.copyFile(source, target);
		
		return fullPath;
	}
	
	public static boolean verifyElementIsPresent(WebDriver ldriver, By elem) {
		boolean isFound = true;
		
		try {
			ldriver.findElement(elem);
		} catch (NoSuchElementException e) {
			isFound = false;
		}
		
		return isFound;
		
	}
	
	public static boolean isElementFound(WebDriver driver, WebElement toLookFor, int timeout) {
		
		  FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)    
				  .pollingEvery(2, TimeUnit.SECONDS)    
				  .ignoring(NoSuchElementException.class); 

		  WebElement element = wait.until(new Function<WebDriver, WebElement>() {  
				  public WebElement apply(WebDriver driver) { 
					  return toLookFor;     
				   }  
		  });  
		
		  if(element != null) {
			  return true;
		  }
		  else {
			  return false;
		  }
	}
}
