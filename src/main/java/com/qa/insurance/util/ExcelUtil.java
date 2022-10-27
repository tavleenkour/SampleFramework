package com.qa.insurance.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil

{
	
	
	public static final String testData = (System.getProperty("user.dir")+ "/src/main/java/com/insurance"
			+ "/qa/testdata/insurance_testdata.xlsx");
	public static Sheet sheet;
	public static Workbook book;
	

			public static Object[][] getTestData(String sheetName) 
			
 	{
				
			FileInputStream file = null;
			try {
				file = new FileInputStream(testData);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try 
			{
				book = WorkbookFactory.create(file);
			} 
			catch (InvalidFormatException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			sheet = book.getSheet(sheetName);

			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];	
			for (int i = 0; i < sheet.getLastRowNum(); i++) 
			{	
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) 
				{					
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
					System.out.println(data[i][j]);
				}
			}
			return data;
		}

		
		}
