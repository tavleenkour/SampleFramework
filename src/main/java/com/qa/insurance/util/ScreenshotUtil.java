package com.qa.insurance.util;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.insurance.base.TestBase;

public class ScreenshotUtil extends TestBase {
	
	static int i = 1;
	public static String takeScreenshotAtEndOfTest() throws IOException 
	{   
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
	   // String path = currentDir + "/screenshots/" + System.currentTimeMillis() + ".png";
	    String path = currentDir + "/screenshots/" + "Screenshot_" + i + ".png";
	    i++;
		FileUtils.copyFile(scrFile, new File(path));
		return path;
	}

}
