package com.oneMap.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		boolean isFound = false;
		
		if(ldriver.findElements(elem).size() > 0) {
			isFound= true;
		}
		
		return isFound;
		
	}
}
