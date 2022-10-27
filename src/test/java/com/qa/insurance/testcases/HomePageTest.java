package com.qa.insurance.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.insurance.base.TestBase;
import com.qa.insurance.pages.HomePage;
import com.qa.insurance.util.ExcelUtil;
import com.qa.insurance.util.TestUtil;


public class HomePageTest extends TestBase
{
	HomePage homePage;
	TestUtil testUtil;
	String sheetName = "data";

	
	public HomePageTest()
	
	{
		super();
		
	}

	
	@BeforeMethod(alwaysRun = true)
	public void setUp() 
	{System.out.println("Set Up suite");
		log.info("Set up in being initialized");
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		
		

	}
	

	@Test(priority=1, groups= {"Functional"})
	public void validateHomePageTitleTest()
	{
		log.info("Validate Home Page Title Test");
		//testUtil.switchToFrame();
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "Smartesting Yest Insurance Simulator");
	}
	
	
	@Test(priority=2, groups= {"Regression"})
	public void validateSimulateButton()
	{
		log.info("Validate Simulate Button Test");
		//testUtil.switchToFrame();
		Boolean loginCTA = homePage.validateSimulateCTA();
		Assert.assertTrue(loginCTA);
	}
	
	
	@DataProvider
	public Object[][] getInsuranceTestData()
	{
		Object data[][] = ExcelUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=3, dataProvider="getInsuranceTestData", dependsOnMethods = { "validateSimulateButton" },groups= {"Regression"})
	public void checkInsuranceValue(String carName, String carModel, String fuelType, String newCar, String firstUse, String name, String licenseIssue, String zipcode, String bonus, String comprehensive , String thirdParty)

	{
		log.info("Check Insurance Value Test");
		log.warn("This test is dependent on Validate Simulate Button Test");
		Boolean annual_fee = homePage.getInfo(carName, carModel, fuelType, newCar, firstUse, name, licenseIssue, zipcode, bonus, comprehensive , thirdParty);
		Assert.assertTrue(annual_fee);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		driver.quit();
		
	}
		
	}
	
	



