package com.oneMap.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.oneMap.utilities.ReadConfig;

public class BaseTestClass {
	
	public ReadConfig config = new ReadConfig();
	public String baseURL = config.getBaseURL();
	public String headlessSetting = config.getHeadlessExecutionValue();
	public String defaultBrowser = config.getDefaultBrowser();
	
	public static Logger LOGGER;
	public static WebDriver driver;
	//public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	@Parameters("browser")
	@BeforeMethod
	public void startApplication(@Optional("chrome") String browser, ITestContext testContext) {
		
		LOGGER = Logger.getLogger("oneMap");
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("/******** Setting Up Testcase:" + testContext.getName() + "********/");
		
		System.out.println("Browser: " + browser);
		
		String path = System.getProperty("user.dir")  + getDriverFilePath() + File.separator;
		String fullPath="";
		boolean isDriverSupported = false;
		boolean isSetToHeadless = false;
		
		if(headlessSetting.equalsIgnoreCase("yes")) {
			isSetToHeadless = true;
		}
		
		if(browser.equalsIgnoreCase("chrome")) {
			fullPath = path + config.getChromeDriverFileName();
			if(exists(fullPath)) {
				isDriverSupported = true;
				System.setProperty("webdriver.chrome.driver", fullPath);
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(isSetToHeadless);
				if(isSetToHeadless) {
					options.addArguments("start-maximized");
					options.addArguments("--window-size=1400,600");
				}
				driver = new ChromeDriver(options);
				//driver.set(new ChromeDriver(options));
			}
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			fullPath = path + config.getFirefoxDriverFileName();
			if(exists(fullPath)) {
				isDriverSupported = true;
				System.setProperty("webdriver.gecko.driver", fullPath);
				FirefoxOptions ffoptions = new FirefoxOptions();
				ffoptions.setHeadless(isSetToHeadless);
				if(isSetToHeadless) {
					ffoptions.addArguments("start-maximized");
					ffoptions.addArguments("--window-size=1400,600");
				}
				driver = new FirefoxDriver(ffoptions);
				//driver.set(new FirefoxDriver(ffoptions));

			}
		}
//		else if(browser.equalsIgnoreCase("Edge")) {
//			fullPath = path + config.getEdgeDriverFileName();
//			if(exists(fullPath)) {
//				isDriverSupported = true;
//				System.setProperty("webdriver.edge.driver", fullPath);
//				driver = new EdgeDriver();
//			}
//		}
		
		if(!isDriverSupported) {
			System.err.print("Selected browser is not supported currently.");
			return;
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		LOGGER.info("Base URL is opened.");
		
		
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
		
	
		LOGGER.info("/******** Tear Down Testcase:" + result.getName() + "********/" );
		driver.quit();
		
	}
	
	
}
