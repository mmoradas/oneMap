package com.oneMap.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.oneMap.utilities.ReadConfig;
import com.oneMap.utilities.Utility;

public class BaseTestClass {
	
	
	public ReadConfig config = new ReadConfig();
	public String baseURL = config.getBaseURL();
	
	public static Logger logger;
	public static WebDriver driver;
			
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		
		logger = Logger.getLogger("oneMap");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("##### Setting Up Testcase. #####");
		
		String path = System.getProperty("user.dir")  + getDriverFilePath() + File.separator;
		String fullPath="";
		boolean isDriverSupported = false;
		
		if(browser.equalsIgnoreCase("chrome")) {
			fullPath = path + config.getChromeDriverFileName();
			if(exists(fullPath)) {
				isDriverSupported = true;
				System.setProperty("webdriver.chrome.driver", fullPath);
				driver = new ChromeDriver();
			}
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			fullPath = path + config.getChromeDriverFileName();
			if(exists(fullPath)) {
				isDriverSupported = true;
				System.setProperty("webdriver.gecko.driver", fullPath);
				driver = new FirefoxDriver();
			}
		}
		else if(browser.equalsIgnoreCase("Edge")) {
			fullPath = path + config.getEdgeDriverFileName();
			if(exists(fullPath)) {
				isDriverSupported = true;
				System.setProperty("webdriver.edge.driver", fullPath);
				driver = new EdgeDriver();
			}
		}
		
		if(!isDriverSupported) {
			System.err.print("Selected browser is not supported currently.");
			return;
		}
		
		driver.manage().window().maximize();
		driver.get(baseURL);
		logger.info("Base URL is opened.");
		
		
	}
	
	private boolean exists(String fullPath) {
		
		File tempFile = new File(fullPath);
		return tempFile.exists();
	}

	private String getDriverFilePath() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		// default path from Windows
		String pathValue = config.getWindowsDriverPath();
		
		if(os.contains("mac")) {
			pathValue = config.getMacDriverPath();
		}
		else if(os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			pathValue = config.getLinuxDriverPath();
		}

		return pathValue;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		if(ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreen(driver, result.getName());
		}
		
		logger.info("##### Tear Down Testcase. #####");
		driver.quit();
		
	}

	
}
