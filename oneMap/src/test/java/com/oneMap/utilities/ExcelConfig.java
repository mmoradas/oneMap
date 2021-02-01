package com.oneMap.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelConfig {
	
	public String TestData[][];
	public String sheetName="";
	
	public ExcelConfig(String name) {
		this.sheetName = name;
	}
	

    public void readExcel() throws IOException {
    	
    	String filePath = System.getProperty("user.dir") + "/TestData/";
    	String fileName = "OneMap_TestData.xlsx";
    	
	    //Create an object of File class to open xlsx file
	    File file = new File(filePath + fileName);
	
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook exWorkBook = null;
	
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));

	    if(fileExtensionName.equals(".xlsx")){
		    //If it is xlsx file then create object of XSSFWorkbook class
		    exWorkBook = new XSSFWorkbook(inputStream);
	    }
	    //Check condition if the file is xls file
	    else if(fileExtensionName.equals(".xls")){
	        exWorkBook = new HSSFWorkbook(inputStream);
	
	    }
	    //Read sheet inside the workbook by its name
	    Sheet exWorkSheet = exWorkBook.getSheet(this.sheetName);
	
	    int rowCount = exWorkSheet.getLastRowNum();
	    int colCount = exWorkSheet.getRow(0).getLastCellNum();
	    
	    String data[][]= new String[rowCount + 1][colCount];
	
		for (int i = 0; i <= rowCount; i++) {
			Row row = exWorkSheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				data[i][j]= row.getCell(j).getStringCellValue();
				System.out.print(row.getCell(j).getStringCellValue()+"|| ");
		     }
		        System.out.println();
		}
		
		this.TestData = data;
		inputStream.close();

    }
    

	public String getData(String testName, String dataVariable) {
		
        int columnsize = TestData[0].length;
        String []headers = new String[columnsize];
        String colName, foundData="";
        for(int i=0; i < columnsize; i++) {
        	colName = TestData[0][i];
        	headers[i]= colName;
        }
        
        int rowSize = TestData.length;
        for(int i=1; i < rowSize; i++) {
        	// Check testname first, if it matches we are interested
        	if(TestData[i][0].equalsIgnoreCase(testName)) {
        		foundData = TestData[i][Arrays.asList(headers).indexOf(dataVariable)];
        	}
        }
		
		return foundData;
	}
    
}