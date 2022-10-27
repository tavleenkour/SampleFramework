package com.qa.insurance.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qa.insurance.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log = Logger.getLogger(TestBase.class);



	
	public TestBase()
	{

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/insurance"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    
		
		}
		
		
	
	
	public static void initialization()
	{

		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("Chrome"))
		{
			driver = WebDriverManager.chromedriver().create();
		}
		else if(browserName.equals("Firefox"))
		{
			driver = WebDriverManager.firefoxdriver().create();
		}
		else if(browserName.equals("Edge"))
		{
			driver = WebDriverManager.edgedriver().create();
		}
		
		log.info("Launching Broswer");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT);
		driver.get(prop.getProperty("url"));
		
	
  }
	
}
