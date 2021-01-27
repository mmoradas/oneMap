package com.oneMap.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		
		File src =  new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public String getBaseURL() {
		
		return prop.getProperty("BaseURL");
	}
	
	public String getWindowsDriverPath() {
		
		return prop.getProperty("Windows_Driver_Path");
	}
	
	public String getLinuxDriverPath() {
		
		return prop.getProperty("Linux_Driver_Path");
	}
	
	public String getMacDriverPath() {
		
		return prop.getProperty("Mac_Driver_Path");
	}
	
	public String getChromeDriverFileName() {
	
		return prop.getProperty("Chrome_Driver_Filename");
	}
	
	public String getFirefoxDriverFileName() {
		
		return prop.getProperty("Firefox_Driver_Filename");
	}

	public String getEdgeDriverFileName() {
		
		return prop.getProperty("Edge_Driver_Filename");
	}
	
	
	public String getHeadlessExecutionValue() {
		
		return prop.getProperty("headlessExecution");
	}
	
}
