package com.qa.insurance.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.insurance.base.TestBase;



public class ScreenshotUtil extends TestBase 

{
	static int i =1; 
	// ****** Utility to capture a screenshot. ********
	public static String takeScreenshotAtEndOfTest() throws IOException 
	{   
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String path = currentDir + "/screenshots/" + "FailedTest_" + i + ".png";
		
		// ******** We can also use current time to name screenshots ********
	   //	String path = currentDir + "/screenshots/" + System.currentTimeMillis() + ".png";

		FileUtils.copyFile(scrFile, new File(path));
		i++;
		return path;
	}

	 }

